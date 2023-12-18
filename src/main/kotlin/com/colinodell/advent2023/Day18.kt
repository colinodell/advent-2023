package com.colinodell.advent2023

class Day18(input: List<String>) {
    private val regex = Regex("""([UDLR]) (\d+) \(#(\w+)\)""")

    private val originalDigPlan = input.map {
        val (dir, dist) = regex.matchEntire(it)!!.destructured
        getDirection(dir[0]).vector() * dist.toLong()
    }

    private val correctedDigPlan = input.map {
        val (_, _, color) = regex.matchEntire(it)!!.destructured
        getDirection(color.last()).vector() * color.dropLast(1).toLong(16)
    }

    private fun getBoundarySize(plan: List<Vector2L>) = plan.sumOf { it.size() }

    private fun getVertices(plan: List<Vector2L>) = plan.scan(Vector2L(0, 0)) { pos, trench -> pos + trench }

    // Calculated via the shoelace formula
    private fun innerArea(vertices: List<Vector2L>) = vertices.zipWithNext().sumOf { (a, b) -> a.x * b.y - b.x * a.y } / 2

    // Calculated via Pick's theorem
    private fun solve(plan: List<Vector2L>) = innerArea(getVertices(plan)) + getBoundarySize(plan) / 2 + 1

    fun solvePart1() = solve(originalDigPlan)

    fun solvePart2() = solve(correctedDigPlan)

    private fun getDirection(d: Char) = when (d) {
        'U', '3' -> Direction.NORTH
        'D', '1' -> Direction.SOUTH
        'L', '2' -> Direction.WEST
        'R', '0' -> Direction.EAST
        else -> throw IllegalArgumentException("Invalid direction: $d")
    }
}
