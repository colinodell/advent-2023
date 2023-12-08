package com.colinodell.advent2023

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExtensionsTest {
    @Nested
    inner class ProductOfTests {
        @Test
        fun `returns 1 given an empty input`() {
            Assertions.assertThat(listOf<Long>().productOf { it }).isEqualTo(1)
        }

        @Test
        fun `returns the product of all items`() {
            Assertions.assertThat(listOf(1L, 2L, 3L).productOf { it }).isEqualTo(6)
        }
    }

    @Nested
    inner class CompareToTests {
        @Test
        fun `returns 0 given two empty inputs`() {
            Assertions.assertThat(listOf<Int>().compareTo(listOf())).isEqualTo(0)
        }

        @Test
        fun `returns 0 given two identical inputs`() {
            Assertions.assertThat(listOf(1, 2, 3).compareTo(listOf(1, 2, 3))).isEqualTo(0)
        }

        @Test
        fun `returns -1 given an input with fewer elements than the other`() {
            Assertions.assertThat(listOf(1, 2, 3).compareTo(listOf(1, 2, 3, 4))).isEqualTo(-1)
        }

        @Test
        fun `returns 1 given an input with more elements than the other`() {
            Assertions.assertThat(listOf(1, 2, 3, 4).compareTo(listOf(1, 2, 3))).isEqualTo(1)
        }

        @Test
        fun `returns -1 when some inner element is less than the other`() {
            Assertions.assertThat(listOf(1, 2, 3).compareTo(listOf(1, 3, 3))).isEqualTo(-1)
        }

        @Test
        fun `returns 1 when some inner element is greater than the other`() {
            Assertions.assertThat(listOf(1, 3, 3).compareTo(listOf(1, 2, 3))).isEqualTo(1)
        }
    }

    @Nested
    inner class SequenceTests {
        @Test
        fun `asRepeatedSequence() repeats the original sequence indefinitely`() {
            Assertions.assertThat(listOf(1, 2, 3).asRepeatedSequence().take(10).toList()).containsExactly(1, 2, 3, 1, 2, 3, 1, 2, 3, 1)
        }
    }

    @Nested
    inner class LCMGCDTests {
        @Test
        fun gcd() {
            Assertions.assertThat(12L.gcd(18L)).isEqualTo(6)
        }

        @Test
        fun lcm() {
            Assertions.assertThat(12L.lcm(18L)).isEqualTo(36)
        }

        @Test
        fun `Collection lcm()`() {
            Assertions.assertThat(listOf(12L, 18L, 24L).lcm()).isEqualTo(72)
        }
    }
}
