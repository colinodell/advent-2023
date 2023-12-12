package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 12: Hot Springs")
class Day12Test {
    private val exampleInput = inputAsListOfString("day12_example.txt")
    private val puzzleInput = inputAsListOfString("day12_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day12(exampleInput).solvePart1()).isEqualTo(21)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day12(puzzleInput).solvePart1()).isEqualTo(7379)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day12(exampleInput).solvePart2()).isEqualTo(525152)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day12(puzzleInput).solvePart2()).isEqualTo(7732028747925)
    }
}
