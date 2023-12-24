package com.colinodell.advent2023

class Day23(input: List<String>) {
    private val trails = input.toGrid()
    private val region = trails.region()
    private val start = trails.region().topLeft + Vector2(1, 0)
    private val end = trails.region().bottomRight - Vector2(1, 0)

    private fun getNeighborsConsideringSlopes(pos: Vector2): Collection<Vector2> {
        if (trails[pos] == 'v') return listOf(pos + Direction.SOUTH.vector())
        if (trails[pos] == '^') return listOf(pos + Direction.NORTH.vector())
        if (trails[pos] == '<') return listOf(pos + Direction.WEST.vector())
        if (trails[pos] == '>') return listOf(pos + Direction.EAST.vector())

        return pos.neighbors()
    }

    private fun isTrail(pos: Vector2) = pos in region && trails[pos] != '#'
    private fun isFork(pos: Vector2) = isTrail(pos) && pos.neighbors().count { isTrail(it) } > 2

    private fun findForks() = buildSet {
        add(start)
        addAll(trails.keys.filter { isFork(it) })
        add(end)
    }

    private fun findDistancesBetweenForks(forks: Set<Vector2>): Map<Vector2, Map<Vector2, Int>> {
        val ret = forks.associateWith { mutableMapOf<Vector2, Int>() }

        for (fork in forks) {
            var positions = setOf(fork)
            val visited = mutableSetOf(fork)
            var distance = 0

            while (positions.isNotEmpty()) {
                distance++
                positions = buildSet {
                    positions.forEach { p ->
                        p.neighbors().filter { isTrail(it) && it !in visited }.forEach { n ->
                            visited.add(n)
                            if (n in forks) {
                                ret[fork]!![n] = distance
                            } else {
                                add(n)
                            }
                        }
                    }
                }
            }
        }

        return ret
    }

    private fun <State> findLongestPathLength(
        start: State,
        reachedEnd: (State) -> Boolean,
        nextStates: (State) -> Map<State, Int>,
        visited: List<State> = emptyList(),
        distance: Int = 0,
    ): Int {
        if (reachedEnd(start)) {
            return distance
        }

        val next = nextStates(start)
            .filter { it.key !in visited }
            .map { pos -> findLongestPathLength(pos.key, reachedEnd, nextStates, visited + start, distance + pos.value) }
            .maxByOrNull { it }

        return next ?: 0
    }

    fun solvePart1() = findLongestPathLength(
        start = start,
        reachedEnd = { it == end },
        nextStates = { pos -> getNeighborsConsideringSlopes(pos).filter { isTrail(it) }.associateWith { 1 } },
    )

    fun solvePart2(): Int {
        val simplified = findDistancesBetweenForks(findForks())

        return findLongestPathLength(
            start = start,
            reachedEnd = { it == end },
            nextStates = { pos -> simplified[pos]!! },
        )
    }
}
