package com.colinodell.advent2023

class Day03(schematic: List<String>) {
    private val symbols = mutableSetOf<Symbol>()
    private val numbers = mutableSetOf<Number>()

    private data class Digit(val value: Int, val position: Vector2)

    private class Number(digits: List<Digit>) {
        val value = digits.reduce { acc, digit -> Digit(acc.value * 10 + digit.value, acc.position) }.value
        val positions = digits.map { it.position }
    }

    private data class Symbol(val value: Char, val position: Vector2)

    init {
        var currentDigits = mutableListOf<Digit>()
        schematic.forEachIndexed { i, line ->
            line.forEachIndexed { j, character ->
                if (character.isDigit()) {
                    currentDigits.add(Digit(character.toString().toInt(), Vector2(i, j)))
                } else {
                    if (character != '.') {
                        symbols.add(Symbol(character, Vector2(i, j)))
                    }

                    if (currentDigits.isNotEmpty()) {
                        numbers.add(Number(currentDigits))
                        currentDigits = mutableListOf()
                    }
                }
            }

            if (currentDigits.isNotEmpty()) {
                numbers.add(Number(currentDigits))
                currentDigits = mutableListOf()
            }
        }
    }

    fun solvePart1() = numbers
        // Find all numbers touching a symbol
        .filter { number ->
            number.positions.any { pos ->
                symbols.any { symbol ->
                    symbol.position.isTouching(pos)
                }
            }
        }
        // Calculate the sum of those numbers
        .sumOf { it.value }

    fun solvePart2() = symbols
        // Find the gear symbols
        .filter { it.value == '*' }
        // Find all numbers touching that gear
        .map { symbol ->
            numbers.filter { number ->
                number.positions.any { pos ->
                    symbol.position.isTouching(pos)
                }
            }
        }
        // We only want gears with exactly two touching numbers
        .filter { it.size == 2 }
        // Calculate the sum of the gear ratios
        .sumOf { it.first().value * it.last().value }
}
