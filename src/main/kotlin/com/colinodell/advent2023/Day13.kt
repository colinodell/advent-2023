package com.colinodell.advent2023

class Day13(input: String) {
    private val patterns = input.split("\n\n").map { it.split("\n") }

    private fun findHorizontalLineOfSymmetry(lines: List<String>, desiredCharDifference: Int = 0) = lines
        .indices
        .drop(1)
        .firstOrNull { calculateSymmetry(lines, it) == desiredCharDifference }
        .default(0)

    // We can just rotate the text 90 degrees and use the same logic as above
    private fun findVerticalLineOfSymmetry(lines: List<String>, desiredCharDifference: Int = 0) = findHorizontalLineOfSymmetry(rotate(lines), desiredCharDifference)

    // Rotates the text 90 degrees
    private fun rotate(lines: List<String>): List<String> {
        val lineLength = lines[0].length
        val rotated = StringBuilder()
        for (i in 0 until lineLength) {
            for (j in lines.indices.reversed()) {
                rotated.append(lines[j][i])
            }
            rotated.append("\n")
        }
        return rotated.toString().trim().split("\n")
    }

    // Calculates how many characters are different between the two halves
    private fun calculateSymmetry(lines: List<String>, pos: Int) = mirror(lines, pos)
        // Zip the two halves together, ignoring the longer part
        .let { (top, bottom) -> top.zip(bottom) }
        // Sum the number of...
        .sumOf { (a, b) ->
            // Differing characters on each comparison line
            a.zip(b).count { (c1, c2) -> c1 != c2 }
        }

    // Splits the text into two halves, mirroring one side
    private fun mirror(lines: List<String>, across: Int) = lines.subList(0, across).reversed() to lines.subList(across, lines.size)

    private fun summarizeReflectionLines(desiredCharDifference: Int = 0) = patterns.sumOf { p -> findVerticalLineOfSymmetry(p, desiredCharDifference) + 100 * findHorizontalLineOfSymmetry(p, desiredCharDifference) }

    fun solvePart1() = summarizeReflectionLines()
    fun solvePart2() = summarizeReflectionLines(desiredCharDifference = 1)
}
