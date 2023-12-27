package com.colinodell.advent2023

class Day24(input: List<String>) {
    private val hailstones = input.map { MovingObject.parse(it) }

    data class Intersection(val point: Vector3D?, val hailstones: Pair<MovingObject, MovingObject>, private val coincident: Boolean = false, private val parallel: Boolean = false) {
        val crossesInFuture = when {
            point == null -> false
            parallel -> false
            coincident -> true
            else -> (point.x > hailstones.first.pos.x) == (hailstones.first.vel.x > 0) &&
                (point.x > hailstones.second.pos.x) == (hailstones.second.vel.x > 0)
        }
    }

    data class MovingObject(val pos: Vector3L, val vel: Vector3L) {
        companion object {
            fun parse(input: String): MovingObject {
                val parts = input.split(" @ ").map { it.split(", ").map { it.toLong() } }
                return MovingObject(Vector3L(parts[0][0], parts[0][1], parts[0][2]), Vector3L(parts[1][0], parts[1][1], parts[1][2]))
            }
        }

        // See https://paulbourke.net/geometry/pointlineplane/
        fun findXYIntersection(other: MovingObject): Intersection {
            val denominator = other.vel.y * vel.x - other.vel.x * vel.y
            val numeratorA = other.vel.x * (pos.y - other.pos.y) - other.vel.y * (pos.x - other.pos.x)
            val numeratorB = vel.x * (pos.y - other.pos.y) - vel.y * (pos.x - other.pos.x)

            if (denominator == 0L) {
                if (numeratorA == 0L && numeratorB == 0L) {
                    return Intersection(null, this to other, coincident = true)
                }

                return Intersection(null, this to other, parallel = true)
            }

            val ua = numeratorA.toDouble() / denominator

            return Intersection(
                Vector3D(pos.x + (ua * vel.x), pos.y + (ua * vel.y), pos.z.toDouble()),
                this to other
            )
        }
    }

    private fun findPossibleVelocities(a: MovingObject, b: MovingObject, dimension: (Vector3L) -> Long) = buildSet {
        if (dimension(a.vel) != dimension(b.vel)) {
            return emptySet<Long>()
        }

        val diff = dimension(b.pos) - dimension(a.pos)
        for (v in -1000L..1000L) {
            if (v == dimension(a.vel)) {
                continue
            }
            if (diff % (v - dimension(a.vel)) == 0L) {
                add(v)
            }
        }
    }

    fun solvePart1(min: Long, max: Long) = hailstones
        .permutations()
        .map { it.first.findXYIntersection(it.second) }
        .count { it.crossesInFuture && it.point!!.x > min && it.point.x < max && it.point.y > min && it.point.y < max }

    fun solvePart2(): Long {
        // Find the only possible velocity for each dimension
        val rvx = hailstones.permutations().map { (a, b) -> findPossibleVelocities(a, b, Vector3L::x) }.filter { it.isNotEmpty() }.reduce { acc, set -> acc.intersect(set) }.first()
        val rvy = hailstones.permutations().map { (a, b) -> findPossibleVelocities(a, b, Vector3L::y) }.filter { it.isNotEmpty() }.reduce { acc, set -> acc.intersect(set) }.first()
        val rvz = hailstones.permutations().map { (a, b) -> findPossibleVelocities(a, b, Vector3L::z) }.filter { it.isNotEmpty() }.reduce { acc, set -> acc.intersect(set) }.first()

        // Solve for the line needed to intersect at that velocity so we can determine the start position
        val (apx, apy, apz) = hailstones[0].pos
        val (avx, avy, avz) = hailstones[0].vel
        val (bpx, bpy, _) = hailstones[1].pos
        val (bvx, bvy, _) = hailstones[1].vel

        // Math based on https://github.com/githuib/AdventOfCode/blob/master/aoc/year2023/day24.py#L73
        val m1 = (avy - rvy).toDouble() / (avx - rvx).toDouble()
        val m2 = (bvy - rvy).toDouble() / (bvx - rvx).toDouble()
        val rpx = (((bpy - (m2 * bpx)) - (apy - (m1 * apx))) / (m1 - m2)).toLong()
        val t = (rpx - apx) / (avx - rvx)

        val rpy = (apy + (avy - rvy) * t)
        val rpz = (apz + (avz - rvz) * t)

        return rpx + rpy + rpz
    }
}
