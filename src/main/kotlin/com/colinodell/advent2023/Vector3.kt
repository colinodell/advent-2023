package com.colinodell.advent2023

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)
}

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
}
