package com.colinodell.advent2023

class Day18(input: List<String>) {
    private data class DigInstruction(val direction: Direction, val distance: Int, val color: String) {
        companion object {
            private val regex = Regex("""([UDLR]) (\d+) \((#\w+)\)""")

            fun parse(input: String): DigInstruction {
                val (dir, dist, color) = regex.matchEntire(input)!!.destructured
                return DigInstruction(Direction.from(dir[0]), dist.toInt(), color)
            }
        }
    }

    private val instructions = input.map { DigInstruction.parse(it) }
    private val grid = instructions.fold(Pair(mutableGridOf(Vector2(0, 0) to ""), Vector2(0, 0))) { (grid, pos), instruction ->
        var pos = pos
        repeat(instruction.distance) {
            pos += instruction.direction.vector()
            grid[pos] = instruction.color
        }
        Pair(grid, pos)
    }.first

    private fun floodFill(start: Vector2): Set<Vector2> {
        val visited = mutableSetOf<Vector2>()
        val toVisit = mutableListOf(start)
        while (toVisit.isNotEmpty()) {
            val pos = toVisit.removeAt(0)
            if (pos in visited || pos in grid) continue
            visited += pos
            toVisit += pos.neighbors()
        }
        return visited
    }

    fun solvePart1() = grid.size + floodFill(Vector2(1, 1)).size
}
