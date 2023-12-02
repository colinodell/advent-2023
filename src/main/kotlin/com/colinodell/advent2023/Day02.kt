package com.colinodell.advent2023

class Day02(input: List<String>) {
    private data class Game(val id: Int, val colorSets: List<Colors>) {
        fun isPossible(colors: Colors) = colorSets.all { it.red <= colors.red && it.green <= colors.green && it.blue <= colors.blue }

        fun fewestColors() = Colors(colorSets.maxOf { it.red }, colorSets.maxOf { it.green }, colorSets.maxOf { it.blue })

        companion object {
            fun fromString(str: String): Game {
                val (id, colors) = str.substring(5).split(": ")
                val colorSets = colors.split("; ").map { Colors.fromString(it) }
                return Game(id.toInt(), colorSets)
            }
        }
    }
    private data class Colors(var red: Int, var green: Int, var blue: Int) {
        companion object {
            fun fromString(colorSet: String): Colors {
                val result = Colors(0, 0, 0)
                colorSet.split(", ").map {
                    val (count, color) = it.split(" ")
                    when (color) {
                        "red" -> result.red = count.toInt()
                        "green" -> result.green = count.toInt()
                        "blue" -> result.blue = count.toInt()
                    }
                }
                return result
            }
        }
    }

    private val games = input.map { Game.fromString(it) }

    fun solvePart1() = games.filter { it.isPossible(Colors(12, 13, 14)) }.sumOf { it.id }
    fun solvePart2() = games.map { it.fewestColors() }.sumOf { it.red * it.green * it.blue }
}
