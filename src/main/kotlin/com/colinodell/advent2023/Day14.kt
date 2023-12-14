package com.colinodell.advent2023

class Day14(input: List<String>) {
    private val platform = Platform(input.toGrid(ignore = '.'))

    private class Platform(originalGrid: Grid<Char>) {
        private val region = originalGrid.region()
        private var roundRockPositions = originalGrid.filterValues { it == 'O' }.keys
        private val cubeRockPositions = originalGrid.filterValues { it == '#' }.keys

        fun tilt(dir: Direction): Platform {
            val movedRocks = mutableSetOf<Vector2>()

            // Sort the rocks so we iterate in the ideal order
            roundRockPositions
                .sortedBy {
                    when (dir) {
                        Direction.NORTH -> it.y
                        Direction.SOUTH -> -it.y
                        Direction.WEST -> it.x
                        Direction.EAST -> -it.x
                    }
                }
                .forEach { oldPos ->
                    // Move as far as we can in this direction until we hit something (or reach the edge)
                    var nextPos = oldPos
                    while (true) {
                        val newPos = nextPos + dir.vector()
                        if (newPos in cubeRockPositions || newPos in movedRocks || newPos !in region) {
                            break
                        }

                        nextPos = newPos
                    }
                    movedRocks.add(nextPos)
                }

            roundRockPositions = movedRocks

            return this
        }

        fun spin(iterations: Int): Platform {
            val cycleCache = mutableMapOf<Set<Vector2>, Int>()

            var i = 0
            while (i < iterations) {
                tilt(Direction.NORTH)
                tilt(Direction.WEST)
                tilt(Direction.SOUTH)
                tilt(Direction.EAST)

                i++

                // Have we seen this exact configuration before? If so, we can skip ahead!
                if (roundRockPositions in cycleCache) {
                    val cycleStart = cycleCache[roundRockPositions]!!
                    val cycleLength = i - cycleStart
                    val remainingIterations = (iterations - i) % cycleLength
                    repeat(remainingIterations) {
                        tilt(Direction.NORTH)
                        tilt(Direction.WEST)
                        tilt(Direction.SOUTH)
                        tilt(Direction.EAST)
                    }
                    break
                }

                cycleCache[roundRockPositions] = i
            }

            return this
        }

        fun calculateTotalLoad() = roundRockPositions.sumOf { region.bottomRight.y - it.y + 1 }
    }

    fun solvePart1() = platform.tilt(Direction.NORTH).calculateTotalLoad()
    fun solvePart2() = platform.spin(1_000_000_000).calculateTotalLoad()
}
