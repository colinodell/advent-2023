package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 23: A Long Walk")
class Day23Test {
    private val exampleInput = inputAsListOfString("day23_example.txt")
    private val puzzleInput = inputAsListOfString("day23_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day23(exampleInput).solvePart1()).isEqualTo(94)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day23(puzzleInput).solvePart1()).isEqualTo(2162)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day23(exampleInput).solvePart2()).isEqualTo(154)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day23(puzzleInput).solvePart2()).isEqualTo(6334)
    }
}
