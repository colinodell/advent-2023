package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 13: Point of Incidence")
class Day13Test {
    private val exampleInput = inputAsText("day13_example.txt")
    private val puzzleInput = inputAsText("day13_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day13(exampleInput).solvePart1()).isEqualTo(405)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day13(puzzleInput).solvePart1()).isEqualTo(41859)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day13(exampleInput).solvePart2()).isEqualTo(400)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day13(puzzleInput).solvePart2()).isEqualTo(30842)
    }
}
