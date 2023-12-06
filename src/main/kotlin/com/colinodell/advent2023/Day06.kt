package com.colinodell.advent2023

class Day06(private val input: List<String>) {
    private fun countWaysToWin(time: Long, recordDistance: Long) = 0.rangeTo(time)
        .takeWhile { holdTime -> ((time - holdTime) * holdTime) <= recordDistance }
        .count()
        .let { time - (it * 2) + 1 }

    fun solvePart1() = run {
        val times = input.first().split(":").last().trim().split(Regex("""\s+""")).map { it.toLong() }
        val recordDistances = input.last().split(":").last().trim().split(Regex("""\s+""")).map { it.toLong() }

        times
            .zip(recordDistances)
            .productOf { countWaysToWin(it.first, it.second) }
    }

    fun solvePart2() = run {
        val time = input.first().replace(" ", "").split(":").last().toLong()
        val recordDistance = input.last().replace(" ", "").split(":").last().toLong()

        countWaysToWin(time, recordDistance)
    }
}
