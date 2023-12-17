package com.colinodell.advent2023

class Day17(input: List<String>) {
    private val grid = input.toGrid().mapValues { it.value.digitToInt() }

    private val start = grid.region().topLeft
    private val goal = grid.region().bottomRight

    private data class State(val pos: Vector2, val dir: Direction, val straightMoves: Int) {
        fun nextStates() = buildList {
            add(State(pos + dir.turnLeft().vector(), dir.turnLeft(), 1))
            add(State(pos + dir.turnRight().vector(), dir.turnRight(), 1))

            if (straightMoves < 3) {
                add(State(pos + dir.vector(), dir, straightMoves + 1))
            }
        }

        fun ultraNextStates() = buildList {
            if (straightMoves == 0 || straightMoves >= 4) {
                add(State(pos + dir.turnLeft().vector(), dir.turnLeft(), 1))
                add(State(pos + dir.turnRight().vector(), dir.turnRight(), 1))
            }

            if (straightMoves < 10) {
                add(State(pos + dir.vector(), dir, straightMoves + 1))
            }
        }
    }

    fun solvePart1() = aStar(
        State(start, Direction.EAST, 0),
        { it.pos == goal },
        { it.nextStates().filter { next -> next.pos in grid } },
        { _, (pos) -> grid[pos]!! },
        { it.pos.manhattanDistanceTo(goal) },
    ).score()

    fun solvePart2() = aStar(
        State(start, Direction.EAST, 0),
        { it.pos == goal && it.straightMoves >= 4 },
        { it.ultraNextStates().filter { next -> next.pos in grid } },
        { _, (pos) -> grid[pos]!! },
        { it.pos.manhattanDistanceTo(goal) },
    ).score()
}
