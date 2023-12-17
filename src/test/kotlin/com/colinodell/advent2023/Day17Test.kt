package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 17: Clumsy Crucible")
class Day17Test {
    private val exampleInput = inputAsListOfString("day17_example.txt")
    private val puzzleInput = inputAsListOfString("day17_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day17(exampleInput).solvePart1()).isEqualTo(102)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day17(puzzleInput).solvePart1()).isEqualTo(1023)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day17(exampleInput).solvePart2()).isEqualTo(94)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day17(puzzleInput).solvePart2()).isEqualTo(1165)
    }
}
