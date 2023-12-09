package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 9: Mirage Maintenance")
class Day09Test {
    private val exampleInput = inputAsListOfString("day09_example.txt")
    private val puzzleInput = inputAsListOfString("day09_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day09(exampleInput).solvePart1()).isEqualTo(114)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day09(puzzleInput).solvePart1()).isEqualTo(1974232246)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day09(exampleInput).solvePart2()).isEqualTo(2)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day09(puzzleInput).solvePart2()).isEqualTo(928)
    }
}
