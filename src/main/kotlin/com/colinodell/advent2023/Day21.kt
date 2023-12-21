package com.colinodell.advent2023

class Day21(input: List<String>) {
    private val grid = input.toGrid()
    private val gridSize = grid.width().also { assert(it == grid.height()) }

    private val start = grid.entries.first { it.value == 'S' }.key

    // Gets the value at the given position in the infinite grid
    private fun get(p: Vector2) = grid[Vector2(p.x.safeMod(gridSize), p.y.safeMod(gridSize))]!!

    private fun generatePlotCounts() = sequence {
        var positions = listOf(start)
        while (true) {
            yield(positions.size)
            positions = positions.flatMap { it.neighbors() }.distinct().filter { get(it) != '#' }
        }
    }

    fun solvePart1(desiredSteps: Int) = generatePlotCounts().take(desiredSteps + 1).last()

    fun solvePart2(desiredSteps: Int): Long {
        val grids = desiredSteps / gridSize
        val remainder = desiredSteps % gridSize
        val counts = generatePlotCounts().take(gridSize * 3 + remainder).toList()

        val a0 = counts[remainder].toLong()
        val a1 = counts[gridSize + remainder].toLong() - counts[remainder]
        val a2 = counts[gridSize * 2 + remainder].toLong() - counts[gridSize + remainder]

        return a0 + (a1 * grids) + (grids * (grids - 1L) / 2L) * (a2 - a1)
    }
}
