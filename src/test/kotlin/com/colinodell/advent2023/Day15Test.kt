package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 15: Lens Library")
class Day15Test {
    private val exampleInput = inputAsText("day15_example.txt")
    private val puzzleInput = inputAsText("day15_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day15(exampleInput).solvePart1()).isEqualTo(1320)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day15(puzzleInput).solvePart1()).isEqualTo(503154)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day15(exampleInput).solvePart2()).isEqualTo(145)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day15(puzzleInput).solvePart2()).isEqualTo(251353)
    }
}
