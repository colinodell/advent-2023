package com.colinodell.advent2023

import kotlin.math.abs

data class Vector2(val x: Int, val y: Int) {
    fun isTouching(other: Vector2) = abs(x - other.x) <= 1 && abs(y - other.y) <= 1

    override fun toString() = "($x, $y)"
}
