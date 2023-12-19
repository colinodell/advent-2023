package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 19: Aplenty")
class Day19Test {
    private val exampleInput = inputAsText("day19_example.txt")
    private val puzzleInput = inputAsText("day19_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day19(exampleInput).solvePart1()).isEqualTo(19114)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day19(puzzleInput).solvePart1()).isEqualTo(323625)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day19(exampleInput).solvePart2()).isEqualTo(167409079868000)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day19(puzzleInput).solvePart2()).isEqualTo(127447746739409)
    }
}
