package com.colinodell.advent2023

import kotlin.math.max
import kotlin.math.min
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

fun <T> T?.default(default: T) = this ?: default
fun <T> Iterable<T>.firstOr(default: T) = firstOrNull() ?: default

/**
 * Counts through the first matching element.
 */
fun <T> Sequence<T>.countUntil(predicate: (T) -> Boolean): Int {
    var count = 0
    for (t in this@countUntil) {
        count++
        if (predicate(t)) {
            break
        }
    }
    return count
}

inline fun <T> Iterable<T>.productOf(predicate: (T) -> Long): Long = fold(1L) { acc, t -> acc * predicate(t) }

fun Int.clamp(min: Int, max: Int) = maxOf(min, minOf(max, this))
fun Long.clamp(min: Long, max: Long) = maxOf(min, minOf(max, this))
fun Double.clamp(min: Double, max: Double) = maxOf(min, minOf(max, this))
fun Int.pow(n: Int) = toDouble().pow(n).toInt()
fun Int.safeMod(other: Int) = ((this % other) + other) % other

fun Long.gcd(other: Long): Long {
    var a = this
    var b = other
    while (b != 0L) {
        val temp = b
        b = a % b
        a = temp
    }
    return a
}
fun Long.lcm(other: Long) = (this * other) / gcd(other)
fun Collection<Long>.lcm(): Long {
    return when (this.size) {
        1 -> this.first()
        2 -> this.first().lcm(this.last())
        else -> this.first().lcm(this.drop(1).lcm())
    }
}

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

fun <T> List<T>.asRepeatedSequence() = generateSequence(0) { (it + 1) % this.size }.map(::get)

val IntRange.size get() = endInclusive - start + 1

/**
 * Returns a new list with the ranges condensed into the smallest possible set of ranges.
 *
 * For example, given the ranges [4..6, 1..3, 7..9], this will return [1..9].
 */
fun Iterable<IntRange>.simplify(): List<IntRange> {
    val sortedRanges = sortedBy { it.first }
    val nonOverlappingRanges = mutableListOf<IntRange>()

    for (range in sortedRanges) {
        if (nonOverlappingRanges.isEmpty()) {
            nonOverlappingRanges.add(range)
        } else {
            val lastRange = nonOverlappingRanges.last()
            if (lastRange.last >= range.first) {
                nonOverlappingRanges[nonOverlappingRanges.lastIndex] = lastRange.first..max(lastRange.last, range.last)
            } else {
                nonOverlappingRanges.add(range)
            }
        }
    }

    return nonOverlappingRanges
}

fun Iterable<IntRange>.intersect(range: IntRange): Collection<IntRange> {
    return buildList {
        for (r in this@intersect) {
            if (r.first >= range.last || r.last <= range.first) {
                continue
            } else {
                add(max(r.first, range.first)..min(r.last, range.last))
            }
        }
    }
}

fun Iterable<IntRange>.subtract(range: IntRange): Collection<IntRange> {
    return buildList {
        for (r in this@subtract) {
            if (r.first > range.last || r.last < range.first) {
                add(r)
            } else {
                if (r.first < range.first) {
                    add(r.first until range.first)
                }
                if (r.last > range.last) {
                    add(range.last + 1 until r.last + 1)
                }
            }
        }
    }
}

fun <T> Collection<T>.permutations() = buildList<Pair<T, T>> {
    val p = this@permutations
    for (i in p.indices) {
        for (j in i + 1 until p.size) {
            add(p.elementAt(i) to p.elementAt(j))
        }
    }
}
