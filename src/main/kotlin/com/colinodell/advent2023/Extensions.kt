package com.colinodell.advent2023

import kotlin.math.pow

fun Int.clamp(min: Int, max: Int) = maxOf(min, minOf(max, this))
fun Int.pow(n: Int) = toDouble().pow(n).toInt()
