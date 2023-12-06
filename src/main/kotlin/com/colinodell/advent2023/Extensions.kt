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

inline fun <T> Iterable<T>.productOf(predicate: (T) -> Long): Long = fold(1L) { acc, t -> acc * predicate(t) }

fun Int.clamp(min: Int, max: Int) = maxOf(min, minOf(max, this))
fun Int.pow(n: Int) = toDouble().pow(n).toInt()

operator fun LongRange.plus(offset: Long): LongRange {
    return (first + offset)..(last + offset)
}
