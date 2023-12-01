package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 1: Trebuchet?!")
class Day01Test {
    private val exampleInputPart1 = inputAsListOfString("day01_example_part1.txt")
    private val exampleInputPart2 = inputAsListOfString("day01_example_part2.txt")
    private val puzzleInput = inputAsListOfString("day01_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day01(exampleInputPart1).solvePart1()).isEqualTo(142)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day01(puzzleInput).solvePart1()).isEqualTo(52974)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day01(exampleInputPart2).solvePart2()).isEqualTo(281)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day01(puzzleInput).solvePart2()).isEqualTo(53340)
    }
}
