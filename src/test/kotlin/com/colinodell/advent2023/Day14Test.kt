package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 14: Parabolic Reflector Dish")
class Day14Test {
    private val exampleInput = inputAsListOfString("day14_example.txt")
    private val puzzleInput = inputAsListOfString("day14_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day14(exampleInput).solvePart1()).isEqualTo(136)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day14(puzzleInput).solvePart1()).isEqualTo(108857)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day14(exampleInput).solvePart2()).isEqualTo(64)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day14(puzzleInput).solvePart2()).isEqualTo(95273)
    }
}
