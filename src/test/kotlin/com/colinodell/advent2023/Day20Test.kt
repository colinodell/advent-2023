package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 20: Pulse Propagation")
class Day20Test {
    private val exampleInput1 = inputAsListOfString("day20_example1.txt")
    private val exampleInput2 = inputAsListOfString("day20_example2.txt")
    private val puzzleInput = inputAsListOfString("day20_input.txt")

    @Test
    fun `Part 1 - Example 1`() {
        assertThat(Day20(exampleInput1).solvePart1()).isEqualTo(32000000)
    }

    @Test
    fun `Part 1 - Example 2`() {
        assertThat(Day20(exampleInput2).solvePart1()).isEqualTo(11687500)
    }

    @Test
    fun `Part 1 - Actual`() {
        assertThat(Day20(puzzleInput).solvePart1()).isEqualTo(944750144)
    }

    @Test
    fun `Part 2 - Actual`() {
        assertThat(Day20(puzzleInput).solvePart2()).isEqualTo(222718819437131L)
    }
}
