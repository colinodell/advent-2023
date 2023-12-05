package com.colinodell.advent2023

import kotlin.math.pow

fun <T> Iterable<T>.chunkedBy(separator: (T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { acc, t ->
        if (separator(t)) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(t)
        }
        acc
    }

fun Int.clamp(min: Int, max: Int) = maxOf(min, minOf(max, this))
fun Int.pow(n: Int) = toDouble().pow(n).toInt()

operator fun LongRange.plus(offset: Long): LongRange {
    return (first + offset)..(last + offset)
}
