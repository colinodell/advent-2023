package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 21: Step Counter")
class Day21Test {
    private val exampleInput = inputAsListOfString("day21_example.txt")
    private val puzzleInput = inputAsListOfString("day21_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day21(exampleInput).solvePart1(1)).isEqualTo(2)
        assertThat(Day21(exampleInput).solvePart1(2)).isEqualTo(4)
        assertThat(Day21(exampleInput).solvePart1(3)).isEqualTo(6)
        assertThat(Day21(exampleInput).solvePart1(6)).isEqualTo(16)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day21(puzzleInput).solvePart1(64)).isEqualTo(3737)
    }
}
