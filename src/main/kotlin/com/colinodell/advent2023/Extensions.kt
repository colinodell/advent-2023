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

fun <T : Comparable<T>> Iterable<T>.compareTo(other: Iterable<T>): Int {
    val otherI = other.iterator()
    for (e in this) {
        if (!otherI.hasNext()) {
            // other has run out of elements, so `this` is larger
            return 1
        }

        val c = e.compareTo(otherI.next())
        if (c != 0) {
            // found a position with a difference
            return c
        }
    }

    if (otherI.hasNext()) {
        // `this` has run out of elements, but other has some more, so other is larger
        return -1
    }

    // they're the same
    return 0
}
