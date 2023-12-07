package com.colinodell.advent2023

class Day07(private val input: List<String>) {
    class Hand(private val cards: List<Char>, val bid: Int, private val joker: Char) : Comparable<Hand> {
        // Number of jokers in this hand
        private val jokerCount = cards.count { it == joker }

        // The strength of this hand as a string, which makes it easy to compare to other hands.
        // Possible values include:
        //   - High card: "11111" (1 of each card)
        //   - One pair: "2111" (2 cards with the same label, 1 of all other cards)
        //   - Two pair: "221" (2 cards with one label, 2 cards with another label, and 1 left over)
        //   - Three of a kind: "311" (3 cards with one label, 1 of all other cards)
        //   - Full house would be "32" (3 of one card, 2 of another)
        //   - Four of a kind would be "41" (4 of one card, 1 of all other cards)
        //   - Five of a kind would be "5" (5 of one card)
        private val strength = cards
            // Group all non-jokers by card count
            .filter { it != joker }
            .groupingBy { it }
            .eachCount()
            // We don't need to know the card labels beyond this point, just the counts
            .map { it.value }
            // No cards? We must have all jokers
            .ifEmpty { listOf(0) }
            // Sort in descending order
            .sortedDescending()
            // If we have jokers, add them to whatever has the highest count to further strengthen the hand
            .toMutableList()
            .apply { if (jokerCount > 0) this[0] += jokerCount }
            // Convert to a string representation for easy comparison
            .joinToString { it.toString() }

        private fun cardValue(card: Char) = when (card) {
            joker -> 0
            'T' -> 10
            'J' -> 11
            'Q' -> 12
            'K' -> 13
            'A' -> 14
            else -> card.toString().toInt()
        }

        // The values of each card, used as a tie-breaker when putting hands in order of strength
        private val cardValues = cards.map { cardValue(it) }

        // Compare by strength, then by card values
        override fun compareTo(other: Hand) = when (val c = strength.compareTo(other.strength)) {
            0 -> cardValues.compareTo(other.cardValues)
            else -> c
        }

        override fun toString() = "$cards ($strength)"
    }

    private fun calculateTotalWinnings(joker: Char = '\u0000') = input
        .map {
            val (cards, bid) = it.split(" ")
            Hand(cards.toList(), bid.toInt(), joker)
        }
        .sorted()
        .mapIndexed { rank, hand -> hand.bid * (rank + 1) }
        .sum()

    fun solvePart1() = calculateTotalWinnings()
    fun solvePart2() = calculateTotalWinnings(joker = 'J')
}
