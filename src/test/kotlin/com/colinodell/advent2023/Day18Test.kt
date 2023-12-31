package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 18: Lavaduct Lagoon")
class Day18Test {
    private val exampleInput = inputAsListOfString("day18_example.txt")
    private val puzzleInput = inputAsListOfString("day18_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day18(exampleInput).solvePart1()).isEqualTo(62)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day18(puzzleInput).solvePart1()).isEqualTo(33491)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day18(exampleInput).solvePart2()).isEqualTo(952408144115)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day18(puzzleInput).solvePart2()).isEqualTo(87716969654406)
    }
}
