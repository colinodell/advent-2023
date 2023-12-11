package com.colinodell.advent2023

class Day11(input: List<String>) {
    private val grid = input.toGrid(ignore = '.')

    private fun expand(g: Grid<Char>, growthFactor: Int): Grid<Char> {
        val f = growthFactor - 1

        val emptyRows = g.rows().filter { y -> g.none { (pos, _) -> pos.y == y } }
        val emptyCols = g.cols().filter { x -> g.none { (pos, _) -> pos.x == x } }

        return g.mapKeys { (pos, _) ->
            Vector2(
                x = pos.x + (f * emptyCols.count { it < pos.x }),
                y = pos.y + (f * emptyRows.count { it < pos.y }),
            )
        }
    }

    private fun calculateAllShortestPaths(galaxies: Collection<Vector2>) = galaxies.sumOf { a ->
        galaxies.filter { b -> b != a }.sumOf { b -> a.manhattanDistanceTo(b).toLong() }
    } / 2

    fun solve(growthFactor: Int) = calculateAllShortestPaths(expand(grid, growthFactor).keys)
}
