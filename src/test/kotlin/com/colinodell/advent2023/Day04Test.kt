package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 4: Scratchcards")
class Day04Test {
    private val exampleInput = inputAsListOfString("day04_example.txt")
    private val puzzleInput = inputAsListOfString("day04_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day04(exampleInput).solvePart1()).isEqualTo(13)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day04(puzzleInput).solvePart1()).isEqualTo(27845)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day04(exampleInput).solvePart2()).isEqualTo(30)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day04(puzzleInput).solvePart2()).isEqualTo(9496801)
    }
}
