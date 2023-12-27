package com.colinodell.advent2023

class Day25(input: List<String>) {
    private val connections = input
        .map { it.split(": ") }
        .flatMap { (left, right) ->
            val rightParts = right.split(" ")
            rightParts.map { left to it }
        }
        .flatMap { (left, right) ->
            listOf(left to right, right to left)
        }
        .groupBy({ it.first }, { it.second })

    fun solvePart1(): Int {
        // Based on https://www.reddit.com/r/adventofcode/comments/18qbsxs/comment/ketzp94
        val nodes = connections.keys.toMutableSet()
        val count = { v: String -> connections[v]!!.filter { !nodes.contains(it) }.size }

        while (nodes.sumOf { count(it) } != 3) {
            nodes.remove(nodes.maxByOrNull(count))
        }

        return nodes.size * (connections.keys.toSet() - nodes).size
    }
}
