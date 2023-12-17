package com.colinodell.advent2023

import java.util.PriorityQueue

data class Seen<State>(val cost: Int, val prev: State?)
private data class Scored<State>(val state: State, val cost: Int, private val heuristic: Int) : Comparable<Scored<State>> {
    override fun compareTo(other: Scored<State>): Int = (cost + heuristic).compareTo(other.cost + other.heuristic)
}

fun <State> aStar(
    start: State,
    reachedEnd: (State) -> Boolean,
    nextStates: (State) -> Iterable<State>,
    cost: (State, State) -> Int,
    heuristic: (State) -> Int,
): SearchResult<State> {
    val seen: MutableMap<State, Seen<State>> = mutableMapOf(start to Seen(0, null))
    val next = PriorityQueue(listOf(Scored(start, 0, heuristic(start))))

    while (next.isNotEmpty()) {
        val (state, score) = next.remove()
        if (reachedEnd(state)) {
            return SearchResult(state, seen)
        }

        nextStates(state)
            .filter { it !in seen }
            .map { n -> Scored(n, score + cost(state, n), heuristic(n)) }
            .forEach { n ->
                next.add(n)
                seen[n.state] = Seen(n.cost, state)
            }
    }

    return SearchResult(null, seen)
}

class SearchResult<State>(private val end: State?, private val seen: Map<State, Seen<State>>) {
    fun end(): State = end ?: throw IllegalStateException("Failed to find a path")
    fun score(): Int = seen[end()]!!.cost
    fun path(): List<State> {
        val path = mutableListOf<State>()
        var current: State? = end()
        while (current != null) {
            path.add(current)
            current = seen[current]!!.prev
        }
        return path.reversed()
    }
}
