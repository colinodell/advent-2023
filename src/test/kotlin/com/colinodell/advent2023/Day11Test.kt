package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 11: Cosmic Expansion")
class Day11Test {
    private val exampleInput = inputAsListOfString("day11_example.txt")
    private val puzzleInput = inputAsListOfString("day11_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day11(exampleInput).solve(growthFactor = 2)).isEqualTo(374)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day11(puzzleInput).solve(growthFactor = 2)).isEqualTo(9918828)
    }

    @Test
    fun `Part 2 - Example`() {
        assertThat(Day11(exampleInput).solve(growthFactor = 10)).isEqualTo(1030)
        assertThat(Day11(exampleInput).solve(growthFactor = 100)).isEqualTo(8410)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day11(puzzleInput).solve(growthFactor = 1_000_000)).isEqualTo(692506533832)
    }
}
