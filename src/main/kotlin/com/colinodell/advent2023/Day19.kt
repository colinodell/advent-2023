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

    private class Rule(val category: Char, op: Char, n: Int, val dest: String) {
        val range = when (op) {
            '<' -> 1 until n
            '>' -> n + 1..4000
            else -> throw IllegalArgumentException("Invalid operator: $op")
        }

        fun apply(part: Map<Char, Int>) = part[category] in range
    }

    private class Workflow(private val rules: List<Rule>, private val default: String) {
        fun next(part: Map<Char, Int>) = rules.firstOrNull { it.apply(part) }?.dest ?: default

        // Returns which workflows can run next, and the filtered ranges that should be passed to them
        fun next(c: Map<Char, Collection<IntRange>>): List<Pair<String, Map<Char, Collection<IntRange>>>> {
            val next = mutableListOf<Pair<String, Map<Char, Collection<IntRange>>>>()
            val remainder = c.toMutableMap()

            rules.forEach { rule ->
                val newPart = remainder.toMutableMap()
                newPart[rule.category] = remainder[rule.category]!!.intersect(rule.range)

                next.add(Pair(rule.dest, newPart))

                remainder[rule.category] = remainder[rule.category]!!.subtract(rule.range)
            }

            // Apply the default to whatever is left
            next.add(Pair(default, remainder))

            return next
        }
    }

    private tailrec fun accepted(part: Map<Char, Int>, workflowId: String): Boolean {
        return when (workflowId) {
            "A" -> true
            "R" -> false
            else -> accepted(part, workflows[workflowId]!!.next(part))
        }
    }

    private fun countCombinations(c: Map<Char, Collection<IntRange>>) = c.values.productOf { it.sumOf { it.size.toLong() } }

    private fun calculateAcceptedCombinations(workflowId: String, combinations: Map<Char, Collection<IntRange>>): Long =
        workflows[workflowId]!!.next(combinations)
            .sumOf { (nextWorkflowId, allowedCombos) ->
                when (nextWorkflowId) {
                    "A" -> countCombinations(allowedCombos)
                    "R" -> 0
                    else -> calculateAcceptedCombinations(nextWorkflowId, allowedCombos)
                }
            }

    fun solvePart1() = parts.filter { accepted(it, "in") }.sumOf { it.values.sum() }

    fun solvePart2() = calculateAcceptedCombinations("in", "xmas".associateWith { listOf(1..4000) })
}
