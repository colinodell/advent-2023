package com.colinodell.advent2023

class Day08(input: List<String>) {
    private data class Node(val left: String, val right: String)

    private val instructions = input.first().toList().asRepeatedSequence()

    private val network = input.drop(2).associate {
        val (self, left, right) = """(\w+) = \((\w+), (\w+)\)""".toRegex().matchEntire(it)!!.destructured
        self to Node(left, right)
    }

    private fun navigate(start: String): Int {
        var steps = 0
        var current = start
        for (dir in instructions) {
            current = when (dir) {
                'L' -> network[current]!!.left
                'R' -> network[current]!!.right
                else -> error("Unknown instruction: $dir")
            }
            steps++
            if (current.endsWith("Z")) {
                return steps
            }
        }
        error("Ran out of instructions (this should never happen, but Kotlin wants me to return something here)")
    }

    fun solvePart1() = navigate("AAA")

    fun solvePart2() = network.keys
        .filter { it.endsWith("A") }
        .map { navigate(it).toLong() }
        .lcm()
}
