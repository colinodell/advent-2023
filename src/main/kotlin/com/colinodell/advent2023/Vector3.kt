package com.colinodell.advent2023

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)

    fun with(x: Int = this.x, y: Int = this.y, z: Int = this.z) = Vector3(x, y, z)

    fun neighbors() = listOf(
        Vector3(x - 1, y, z),
        Vector3(x + 1, y, z),
        Vector3(x, y - 1, z),
        Vector3(x, y + 1, z),
        Vector3(x, y, z - 1),
        Vector3(x, y, z + 1)
    )
}

data class Vector3L(val x: Long, val y: Long, val z: Long)
data class Vector3D(val x: Double, val y: Double, val z: Double)

data class Cuboid(val start: Vector3, val end: Vector3) {
    init {
        require(start.x <= end.x)
        require(start.y <= end.y)
        require(start.z <= end.z)
    }

    fun shift(x: Int = 0, y: Int = 0, z: Int = 0) = Cuboid(start + Vector3(x, y, z), end + Vector3(x, y, z))

    operator fun plus(other: Vector3) = Cuboid(start + other, end + other)
    operator fun minus(other: Vector3) = Cuboid(start - other, end - other)

    fun intersects(other: Cuboid) = start.x <= other.end.x && end.x >= other.start.x &&
        start.y <= other.end.y && end.y >= other.start.y &&
        start.z <= other.end.z && end.z >= other.start.z

    fun contains(vector: Vector3) = vector.x in start.x..end.x && vector.y in start.y..end.y && vector.z in start.z..end.z
}
