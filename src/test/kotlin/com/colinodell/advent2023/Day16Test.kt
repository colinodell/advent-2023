package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 16: The Floor Will Be Lava")
class Day16Test {
    private val exampleInput = inputAsListOfString("day16_example.txt")
    private val puzzleInput = inputAsListOfString("day16_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day16(exampleInput).solvePart1()).isEqualTo(46)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day16(puzzleInput).solvePart1()).isEqualTo(7034)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day16(exampleInput).solvePart2()).isEqualTo(51)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day16(puzzleInput).solvePart2()).isEqualTo(7759)
    }
}
