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
}
