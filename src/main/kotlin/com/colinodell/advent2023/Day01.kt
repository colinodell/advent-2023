package com.colinodell.advent2023

class Day01(private val calibrationDocument: List<String>) {
    private val digitMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )

    private fun filterDigits(str: String, withWords: Boolean): String {
        if (!withWords) {
            return str.filter { it.isDigit() }
        }

        return buildString {
            for (i in str.indices) {
                if (str[i].isDigit()) {
                    append(str[i])
                    continue
                }

                val remainder = str.substring(i)
                for ((word, digit) in digitMap) {
                    if (remainder.startsWith(word)) {
                        append(digit)
                        break
                    }
                }
            }
        }
    }

    private fun solve(withWords: Boolean) = calibrationDocument
        .map { filterDigits(it, withWords) }
        .map { it.first().toString() + it.last().toString() }
        .sumOf { it.toInt() }

    fun solvePart1() = solve(withWords = false)
    fun solvePart2() = solve(withWords = true)
}
