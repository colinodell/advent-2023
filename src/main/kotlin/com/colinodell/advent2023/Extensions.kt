package com.colinodell.advent2023

fun Int.clamp(min: Int, max: Int) = maxOf(min, minOf(max, this))
