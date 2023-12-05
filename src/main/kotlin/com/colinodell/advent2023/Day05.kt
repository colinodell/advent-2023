package com.colinodell.advent2023

import kotlin.math.max
import kotlin.math.min

class Day05(input: List<String>) {
    private data class Map(val ranges: List<Range>) {
        fun apply(input: List<LongRange>): List<LongRange> {
            val rangesToMap = input.toMutableList()

            // Map each source range to its destination
            val mappedRanges = mutableListOf<LongRange>()
            while (rangesToMap.isNotEmpty()) {
                val inputRange = rangesToMap.removeFirst()

                val mapping = ranges.find {
                    inputRange.first <= it.sourceRange.last && inputRange.last >= it.sourceRange.first
                }

                // No mapping? Use the original range as-is
                if (mapping == null) {
                    mappedRanges += inputRange
                    continue
                }

                val (mapRange, offset) = mapping

                // Map the overlapping part of the range
                mappedRanges += (max(inputRange.first, mapRange.first) + offset)..(min(inputRange.last, mapRange.last) + offset)

                // If the mapping didn't cover the entire range we'll need to map the remaining parts
                if (inputRange.first < mapRange.first) {
                    // Mapping only covered the end of the range, so map the beginning (in a future iteration)
                    rangesToMap += inputRange.first until mapRange.first
                }
                if (inputRange.last > mapRange.last) {
                    // Mapping only covered the beginning of the range, so map the end (in a future iteration)
                    rangesToMap += (mapRange.last + 1)..inputRange.last
                }
            }

            return mappedRanges
        }
    }

    private data class Range(val sourceRange: LongRange, val offset: Long)

    private class Chain(private val maps: List<Map>) {
        fun apply(seeds: List<LongRange>) = maps.fold(seeds) { ranges, map -> map.apply(ranges) }
    }

    private val startingSeeds = input.first().split(": ").last().split(" ").map { it.toLong() }

    private val chain = input
        // Ignore the first two lines; those contain the starting seeds
        .drop(2)
        // Split the remaining lines into sections, each of which contains a single mapping
        .chunkedBy { it.isBlank() }
        // Parse each section into a Map
        .map { section ->
            Map(
                section.drop(1).map { line ->
                    val (destinationStart, sourceStart, length) = line.split(" ").map { it.toLong() }
                    Range(LongRange(sourceStart, sourceStart + length - 1), destinationStart - sourceStart)
                }
            )
        }
        .let { maps -> Chain(maps) }

    fun solvePart1() = startingSeeds
        // Each seed is a single number
        .map { it..it }
        .let { chain.apply(it) }
        .minOf { it.first }

    fun solvePart2() = startingSeeds
        .let {
            // Every two "seeds" is actually a range
            it.chunked(2).map { (start, length) -> start until start + length }
        }
        .let { chain.apply(it) }
        .minOf { it.first }
}
