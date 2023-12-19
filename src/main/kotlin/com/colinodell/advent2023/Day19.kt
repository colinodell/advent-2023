package com.colinodell.advent2023

class Day19(input: String) {
    private val workflows: Map<String, Workflow> = input
        .split("\n\n")
        .first()
        .split("\n").associate { line ->
            val id = line.takeWhile { it != '{' }
            val rules = line.split('{').last().trimEnd('}').split(",")

            id to Workflow(
                rules.dropLast(1).map { rule ->
                    val (category, op, n, dest) = Regex("""(\w)([<>])(\d+):(\w+)""").matchEntire(rule)!!.destructured
                    Rule(category[0], op[0], n.toInt(), dest)
                },
                rules.last()
            )
        }

    private val parts = input
        .split("\n\n")
        .last()
        .split("\n")
        .map { line ->
            // Example: {x=787,m=2655,a=1222,s=2876}
            line.drop(1).dropLast(1).split(",").associate { part ->
                val (k, v) = part.split("=")
                k[0] to v.toInt()
            }
        }

    private data class Rule(val category: Char, val op: Char, val n: Int, val dest: String) {
        fun apply(part: Map<Char, Int>) = when (op) {
            '<' -> part[category]!! < n
            '>' -> part[category]!! > n
            else -> throw IllegalArgumentException("Invalid operator: $op")
        }
    }

    private class Workflow(private val rules: List<Rule>, private val default: String) {
        fun apply(part: Map<Char, Int>) = rules.firstOrNull { it.apply(part) }?.dest ?: default
    }

    private tailrec fun accepted(part: Map<Char, Int>, workflowId: String): Boolean {
        return when (workflowId) {
            "A" -> true
            "R" -> false
            else -> accepted(part, workflows[workflowId]!!.apply(part))
        }
    }

    fun solvePart1() = parts.filter { accepted(it, "in") }.sumOf { it.values.sum() }
}
