package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 8: Haunted Wasteland")
class Day08Test {
    private val part1example1 = inputAsListOfString("day08_example1.txt")
    private val part1example2 = inputAsListOfString("day08_example2.txt")
    private val part2example1 = inputAsListOfString("day08_example3.txt")
    private val puzzleInput = inputAsListOfString("day08_input.txt")

    @Test
    fun `Part 1 - Example 1`() {
        assertThat(Day08(part1example1).solvePart1()).isEqualTo(2)
    }

    @Test
    fun `Part 1 - Example 2`() {
        assertThat(Day08(part1example2).solvePart1()).isEqualTo(6)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day08(puzzleInput).solvePart1()).isEqualTo(14429)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day08(part2example1).solvePart2()).isEqualTo(6)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day08(puzzleInput).solvePart2()).isEqualTo(10921547990923)
    }
}
