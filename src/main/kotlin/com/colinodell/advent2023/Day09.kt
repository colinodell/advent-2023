package com.colinodell.advent2023

class Day09(input: List<String>) {
    private val histories = input.map { it.split(" ").map { it.toInt() } }

    fun solvePart1() = histories.sumOf { it.predictNextValue() }
    fun solvePart2() = histories.sumOf { it.extrapolateBackwards() }

    private fun List<Int>.predictNextValue(): Int = when {
        all { it == 0 } -> 0
        else -> last() + differences().predictNextValue()
    }

    private fun List<Int>.extrapolateBackwards(): Int = when {
        all { it == 0 } -> 0
        else -> first() - differences().extrapolateBackwards()
    }

    private fun List<Int>.differences() = mapIndexed { index, i -> if (index == 0) 0 else i - get(index - 1) }.drop(1)
}
