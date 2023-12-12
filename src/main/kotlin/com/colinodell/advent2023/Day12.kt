package com.colinodell.advent2023

class Day12(input: List<String>) {
    private val records = input.map {
        Record(
            conditions = it.split(" ").first(),
            expected = it.split(" ").last().split(",").map { it.toInt() }
        )
    }

    private data class Record(val conditions: String, val expected: List<Int>) {
        fun unfold(x: Int) = Record(
            conditions = (1..x).joinToString("?") { conditions },
            expected = (1..x).flatMap { expected }
        )
    }

    private val cache = mutableMapOf<Triple<String, List<Int>, Int>, Long>()

    private fun calculateSolutions(conditions: String, expected: List<Int>, damagedEarlierInGroup: Int = 0): Long {
        val cacheKey = Triple(conditions, expected, damagedEarlierInGroup)
        if (cache.containsKey(cacheKey)) return cache[cacheKey]!!

        if (conditions.isEmpty()) {
            // We've reached the end - did everything match up?
            return if (expected.isEmpty() && damagedEarlierInGroup == 0) 1 else 0
        }

        var result = 0L

        val next = if (conditions[0] == '?') listOf('.', '#') else listOf(conditions[0])
        for (c in next) {
            result += when {
                c == '#' ->
                    calculateSolutions(conditions.drop(1), expected, damagedEarlierInGroup + 1)
                damagedEarlierInGroup == 0 ->
                    calculateSolutions(conditions.drop(1), expected)
                expected.isNotEmpty() && expected[0] == damagedEarlierInGroup ->
                    calculateSolutions(conditions.drop(1), expected.drop(1))
                else -> 0L
            }
        }

        cache[cacheKey] = result

        return result
    }

    private fun sumOfSolutions(folds: Int = 1) = records.map { it.unfold(folds) }.sumOf { calculateSolutions(it.conditions + '.', it.expected) }

    fun solvePart1() = sumOfSolutions()
    fun solvePart2() = sumOfSolutions(folds = 5)
}
