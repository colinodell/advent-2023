package com.colinodell.advent2023

class Day21(input: List<String>) {
    private val grid = input.toGrid()
    private val gridSize = grid.width().also { assert(it == grid.height()) }

    private val start = grid.entries.first { it.value == 'S' }.key

    private fun generatePlotCounts() = sequence {
        var positions = setOf(start)
        while (true) {
            yield(positions.size)
            positions = positions.flatMap { it.neighbors().filter { grid[it] != '#' } }.toSet()
        }
    }

    fun solvePart1(desiredSteps: Int) = generatePlotCounts().take(desiredSteps + 1).last()
}
