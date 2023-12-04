package com.colinodell.advent2023

class Day04(input: List<String>) {
    private val scratchcards = input.map { Scratchcard.parse(it) }

    private data class Scratchcard(val id: Int, val winningNumbers: Set<Int>, val numbersYouHave: Set<Int>) {
        val matchingNumbers get() = winningNumbers.intersect(numbersYouHave).size

        companion object {
            private val scratchcardParser = Regex("""Card\s+(\d+):\s+((?:\d+\s*)+)\s+\|\s+((?:\d+\s*)+)""")
            private val spaces = Regex("""\s+""")

            fun parse(input: String) = scratchcardParser
                .matchEntire(input)
                .let { requireNotNull(it) { "Invalid scratchcard: $input" } }
                .let {
                    Scratchcard(
                        id = it.groupValues[1].toInt(),
                        winningNumbers = it.groupValues[2].split(spaces).map { it.toInt() }.toSet(),
                        numbersYouHave = it.groupValues[3].split(spaces).map { it.toInt() }.toSet()
                    )
                }
        }
    }

    fun solvePart1() = scratchcards
        .map { it.matchingNumbers }
        .sumOf { if (it == 0) 0 else 2.pow(it - 1) }

    fun solvePart2(): Int {
        val counts = scratchcards.map { 1 }.toMutableList()
        for (i in scratchcards.indices) {
            for (j in 0 until counts[i]) {
                for (k in 0 until scratchcards[i].matchingNumbers) {
                    counts[i + 1 + k]++
                }
            }
        }

        return counts.sum()
    }
}
