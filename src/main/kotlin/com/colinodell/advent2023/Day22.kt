package com.colinodell.advent2023

class Day22(input: List<String>) {
    private val bricks = input
        .flatMap { it.split("~") }
        .map { it.split(",").map { it.toInt() } }
        .map { Vector3(it[0], it[1], it[2]) }
        .chunked(2)
        .map { (start, end) -> Cuboid(start, end) }
        .let { makeThemFall(it) }

    private fun isSupporting(bottom: Cuboid, top: Cuboid): Boolean {
        return bottom != top && bottom.shift(z = 1).intersects(top)
    }

    private fun canDisintegrate(brick: Cuboid) = bricks
        .filter { isSupporting(brick, it) }
        .none { supported -> bricks.count { isSupporting(it, supported) } == 1 }

    private fun makeThemFall(bricks: List<Cuboid>): List<Cuboid> {
        val next = mutableListOf<Cuboid>()
        val settled = mutableSetOf<Cuboid>()

        fun hasSettled(brick: Cuboid) = brick.start.z == 1 || settled.any { other -> isSupporting(other, brick) }

        for (brick in bricks.sortedBy { it.start.z }) {
            var b = brick
            while (!hasSettled(b)) {
                b = b.shift(z = -1)
            }
            settled.add(b)
            next.add(b)
        }

        return next
    }

    fun solvePart1() = bricks.count { canDisintegrate(it) }
}
