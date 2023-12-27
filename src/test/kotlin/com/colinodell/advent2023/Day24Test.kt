package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 24: Never Tell Me The Odds")
class Day24Test {
    private val exampleInput = inputAsListOfString("day24_example.txt")
    private val puzzleInput = inputAsListOfString("day24_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day24(exampleInput).solvePart1(7, 27)).isEqualTo(2)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day24(puzzleInput).solvePart1(200000000000000, 400000000000000)).isEqualTo(20847)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day24(puzzleInput).solvePart2()).isEqualTo(908621716620524)
    }
}
