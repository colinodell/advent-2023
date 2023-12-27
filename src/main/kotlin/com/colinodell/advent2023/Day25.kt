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

    private fun disconnect(conns: Map<String, List<String>>, a: String, b: String): Map<String, List<String>> {
        val conns = conns.toMutableMap()
        conns[a] = conns[a]?.filter { it != b } ?: emptyList()
        conns[b] = conns[b]?.filter { it != a } ?: emptyList()

        return conns
    }

    private fun getGroups(conns: Map<String, List<String>>): List<Set<String>> {
        val visited = mutableSetOf<String>()
        val groups = mutableListOf<Set<String>>()

        for (component in conns.keys) {
            if (visited.contains(component)) {
                continue
            }

            val group = mutableSetOf<String>()
            groups.add(group)
            val queue = mutableListOf(component)
            while (queue.isNotEmpty()) {
                val current = queue.removeAt(0)
                if (visited.contains(current)) {
                    continue
                }
                visited.add(current)
                group.add(current)
                queue.addAll(conns[current] ?: emptyList())
            }
        }

        return groups
    }

    // Figure out which three pairs of wires we need to disconnect so that
    // we're left with exactly 2 groups of wires. Return the product of the
    // sizes of those two groups.
    private fun bruteForce(conns: Map<String, List<String>>, cutsMade: Int = 0): Int {
        if (cutsMade > 3) {
            return -1
        }

        val groups = getGroups(conns)
        if (groups.size == 2) {
            return groups.fold(1) { acc, group ->
                acc * group.size
            }
        }

        for (a in conns.keys.toList().shuffled()) {
            for (b in conns[a]?.shuffled() ?: emptyList()) {
                val newConns = disconnect(conns, a, b)
                val result = bruteForce(newConns, cutsMade + 1)
                if (result != -1) {
                    return result
                }
            }
        }

        return -1
    }

    fun solvePart1() = bruteForce(connections)
}
