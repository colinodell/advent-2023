package com.colinodell.advent2023

class Day22(input: List<String>) {
    private val bricks = input
        .flatMap { it.split("~") }
        .map { it.split(",").map { it.toInt() } }
        .map { Vector3(it[0], it[1], it[2]) }
        .chunked(2)
        .map { (start, end) -> Cuboid(start, end) }
        .let { makeThemFall(it).first }

    private fun isSupporting(bottom: Cuboid, top: Cuboid) = bottom.end.z + 1 == top.start.z && bottom.shift(z = 1).intersects(top)

    private fun supportsOtherBricks(brick: Cuboid) = bricks
        .filter { isSupporting(brick, it) }
        .any { supported -> bricks.count { isSupporting(it, supported) } == 1 }

    private fun makeThemFall(bricks: List<Cuboid>): Pair<List<Cuboid>, Int> {
        val next = mutableListOf<Cuboid>()
        val settled = mutableMapOf<Int, MutableSet<Cuboid>>()
        var moved = 0

        fun hasSettled(brick: Cuboid) = brick.start.z == 1 || settled[brick.start.z - 1]?.any { other -> isSupporting(other, brick) } ?: false

        for (brick in bricks.sortedBy { it.start.z }) {
            var b = brick
            while (!hasSettled(b)) {
                b = b.shift(z = -1)
            }
            next.add(b)
            settled[b.end.z]?.add(b) ?: settled.put(b.end.z, mutableSetOf(b))
            if (b != brick) moved++
        }

        return Pair(next, moved)
    }

    fun solvePart1() = bricks.count { !supportsOtherBricks(it) }

    fun solvePart2() = bricks.filter { supportsOtherBricks(it) }.sumOf { supportingBrick ->
        makeThemFall(bricks.filter { it != supportingBrick }).second
    }
}
