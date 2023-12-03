package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 3: Gear Ratios")
class Day03Test {
    private val exampleInput = inputAsListOfString("day03_example.txt")
    private val puzzleInput = inputAsListOfString("day03_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day03(exampleInput).solvePart1()).isEqualTo(4361)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day03(puzzleInput).solvePart1()).isEqualTo(550934)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day03(exampleInput).solvePart2()).isEqualTo(467835)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day03(puzzleInput).solvePart2()).isEqualTo(81997870)
    }
}
