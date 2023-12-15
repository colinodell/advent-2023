package com.colinodell.advent2023

class Day15(input: String) {
    private val steps = input.split(",")

    private fun hash(step: String) = step.fold(0) { acc, c -> (acc + c.code) * 17 % 256 }

    private data class Step(val lens: String, val op: Char, val focalLength: Int?) {
        companion object {
            fun parse(input: String): Step {
                val (label, op, focalLength) = Regex("""([a-z]+)([=-])(\d+)?""").matchEntire(input)!!.destructured
                return Step(label, op[0], focalLength.toIntOrNull())
            }
        }
    }

    fun solvePart1() = steps.sumOf { hash(it) }

    fun solvePart2() = steps
        // Convert each step string into a Step object
        .map { Step.parse(it) }
        // Create an array of 256 boxes, each containing a list of lenses
        .fold(Array(256) { mutableListOf<Pair<String, Int>>() }) { boxes, step ->
            val targetBox = hash(step.lens)
            if (step.op == '-') {
                // Remove lens via filter, which reindexes the list if needed
                boxes[targetBox] = boxes[targetBox].filter { it.first != step.lens }.toMutableList()
            } else if (boxes[targetBox].any { it.first == step.lens }) {
                // Replace existing lens in-place
                boxes[targetBox] = boxes[targetBox].map { if (it.first == step.lens) Pair(it.first, step.focalLength!!) else it }.toMutableList()
            } else {
                // Add new lens to the end
                boxes[targetBox].add(Pair(step.lens, step.focalLength!!))
            }
            boxes
        }
        // Calculate the focusing power
        .flatMapIndexed { boxNumber, lenses ->
            lenses.mapIndexed { slot, lens ->
                (boxNumber + 1) * (slot + 1) * (lens.second)
            }
        }
        // Sum the focusing powers
        .sum()
}
