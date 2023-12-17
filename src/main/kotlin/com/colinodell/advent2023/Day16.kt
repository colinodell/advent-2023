package com.colinodell.advent2023

class Day16(input: List<String>) {
    private val grid = input.toGrid()
    private val region = grid.region()

    private data class Beam(val pos: Vector2, val direction: Direction) {
        private fun forward() = Beam(pos + direction.vector(), direction)
        private fun left() = Beam(pos + direction.turnLeft().vector(), direction.turnLeft())
        private fun right() = Beam(pos + direction.turnRight().vector(), direction.turnRight())

        fun nextBeams(tile: Char?): List<Beam> = when (tile) {
            '/' -> when (direction) {
                Direction.NORTH, Direction.SOUTH -> listOf(right())
                Direction.WEST, Direction.EAST -> listOf(left())
            }

            '\\' -> when (direction) {
                Direction.NORTH, Direction.SOUTH -> listOf(left())
                Direction.WEST, Direction.EAST -> listOf(right())
            }

            '|' -> when (direction) {
                Direction.NORTH, Direction.SOUTH -> listOf(forward())
                Direction.WEST, Direction.EAST -> listOf(left(), right())
            }

            '-' -> when (direction) {
                Direction.NORTH, Direction.SOUTH -> listOf(left(), right())
                Direction.WEST, Direction.EAST -> listOf(forward())
            }

            else -> listOf(forward())
        }
    }

    private fun solve(startingBeam: Beam): Int {
        var beams = setOf(startingBeam)
        val energized = mutableSetOf<Beam>()

        while (beams.isNotEmpty()) {
            energized.addAll(beams)

            beams = beams
                .flatMap { beam -> beam.nextBeams(grid[beam.pos]) }
                .filter { beam -> beam.pos in region && beam !in energized }
                .toSet()
        }

        return energized.distinctBy { it.pos }.size
    }

    fun solvePart1() = solve(Beam(Vector2(0, 0), Direction.EAST))

    fun solvePart2() = listOf(
        (0 until grid.width()).map { x -> Beam(Vector2(x, 0), Direction.SOUTH) },
        (0 until grid.width()).map { x -> Beam(Vector2(x, grid.height() - 1), Direction.NORTH) },
        (0 until grid.height()).map { y -> Beam(Vector2(0, y), Direction.EAST) },
        (0 until grid.height()).map { y -> Beam(Vector2(grid.width() - 1, y), Direction.WEST) },
    )
        .flatten()
        .maxOf { solve(it) }
}
