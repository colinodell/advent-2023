package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 8: Haunted Wasteland")
class Day10Test {
    private val part1example1 = inputAsListOfString("day10_part1_example1.txt").toGrid()
    private val part1example2 = inputAsListOfString("day10_part1_example2.txt").toGrid()
    private val part2example1 = inputAsListOfString("day10_part2_example1.txt").toGrid()
    private val part2example2 = inputAsListOfString("day10_part2_example2.txt").toGrid()
    private val part2example3 = inputAsListOfString("day10_part2_example3.txt").toGrid()
    private val part2example4 = inputAsListOfString("day10_part2_example4.txt").toGrid()
    private val puzzleInput = inputAsListOfString("day10_input.txt").toGrid()

    @Test
    fun `Part 1 - Example 1`() {
        assertThat(Day10(part1example1).solvePart1()).isEqualTo(4)
    }

    @Test
    fun `Part 1 - Example 2`() {
        assertThat(Day10(part1example2).solvePart1()).isEqualTo(8)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day10(puzzleInput).solvePart1()).isEqualTo(6754)
    }

    @Test
    fun `Part 2 - Example 1`() {
        assertThat(Day10(part2example1).solvePart2()).isEqualTo(4)
    }

    @Test
    fun `Part 2 - Example 2`() {
        assertThat(Day10(part2example2).solvePart2()).isEqualTo(4)
    }

    @Test
    fun `Part 2 - Example 3`() {
        assertThat(Day10(part2example3).solvePart2()).isEqualTo(8)
    }

    @Test
    fun `Part 2 - Example 4`() {
        assertThat(Day10(part2example4).solvePart2()).isEqualTo(10)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day10(puzzleInput).solvePart2()).isEqualTo(567)
    }
}
