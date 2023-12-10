package com.colinodell.advent2023

class Day10(private val tiles: Grid<Char>) {
    private val start = tiles.entries.first { it.value == 'S' }.key

    private val pipeEntrances = mapOf(
        // Directions below are the direction you can enter the pipe from
        '|' to setOf(Direction.UP, Direction.DOWN),
        '-' to setOf(Direction.LEFT, Direction.RIGHT),
        'L' to setOf(Direction.DOWN, Direction.LEFT),
        'J' to setOf(Direction.DOWN, Direction.RIGHT),
        '7' to setOf(Direction.UP, Direction.RIGHT),
        'F' to setOf(Direction.UP, Direction.LEFT),
    )

    private fun canEnter(from: Vector2, to: Vector2): Boolean {
        if (tiles[to] == 'S') return canEnter(to, from)

        val dir = from.directionTo(to) ?: return false
        return pipeEntrances[tiles[to]]?.contains(dir) ?: false
    }

    // Two pipes connect if they can be entered from each other
    private fun connects(from: Vector2, to: Vector2) = canEnter(from, to) && canEnter(to, from)

    // Returns a new Grid containing only the loop (and with 'S' replaced with the correct pipe shape)
    private fun getLoop(): Grid<Char> {
        val loopPoints = mutableSetOf(start)
        // The pipe is a loop, so pick a random direction to move in from the start
        var prev = start
        var pos = start.neighbors().first { canEnter(start, it) }
        // Keep moving until we're back at the start
        while (pos != start) {
            loopPoints += pos
            pos = pos.neighbors().first { it != prev && connects(pos, it) }.also { prev = pos }
        }

        // Create a new grid containing only the loop
        val loop = tiles.filter { it.key in loopPoints }.toMutableMap()

        // Replace 'S' with the correct pipe shape for our algorithm to work
        loop[start] = this.pipeEntrances.entries.first { (_, v) ->
            v.contains(start.directionTo(start.neighbors().first { it in loopPoints }))
        }.key

        return loop
    }

    // The furthest point away is half the loop length
    fun solvePart1() = getLoop().size / 2

    // Find the number of enclosed points
    fun solvePart2(): Int {
        val pipes = getLoop()

        // We will find enclosed points by scanning horizontally.
        // If we hit a vertical piece '|', we're either entering or exiting the loop.
        // Squeezing between pipes will be handled by pretending we're traversing the top of the pipe;
        // so '|', 'L', and 'J' are considered vertical pieces but '7' and 'F' are not because we can
        // "squeeze" along the top of them.
        val verticalPipes = setOf('|', 'L', 'J')

        var enclosedPoints = 0
        for (y in 0 until pipes.height()) {
            var inside = false
            for (x in 0 until pipes.width()) {
                val pos = Vector2(x, y)
                if (pipes[pos] in verticalPipes) {
                    // We're crossing a vertical pipe
                    inside = !inside
                } else if (inside && pos !in pipes) {
                    // We're on an enclosed point inside the loop
                    enclosedPoints++
                }
            }
        }

        return enclosedPoints
    }
}
