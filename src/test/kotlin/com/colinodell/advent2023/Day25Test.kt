package com.colinodell.advent2023

import com.colinodell.advent2023.Inputs.inputAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 25: Snowverload")
class Day25Test {
    private val exampleInput = inputAsListOfString("day25_example.txt")
    private val puzzleInput = inputAsListOfString("day25_input.txt")

    @Test
    fun `Part 1 - Example`() {
        assertThat(Day25(exampleInput).solvePart1()).isEqualTo(54)
    }
}
