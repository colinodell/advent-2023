package com.colinodell.advent2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Vector3")
class Vector3Test {
    @Test
    fun `Constructor and Properties`() {
        val vector = Vector3(1, 2, 3)
        assertThat(vector.x).isEqualTo(1)
        assertThat(vector.y).isEqualTo(2)
        assertThat(vector.z).isEqualTo(3)
    }

    @Test
    fun `Addition`() {
        val vector = Vector3(1, 2, 3) + Vector3(4, 5, 6)
        assertThat(vector.x).isEqualTo(5)
        assertThat(vector.y).isEqualTo(7)
        assertThat(vector.z).isEqualTo(9)
    }

    @Test
    fun `Subtraction`() {
        val vector = Vector3(4, 6, 8) - Vector3(1, 2, 3)
        assertThat(vector.x).isEqualTo(3)
        assertThat(vector.y).isEqualTo(4)
        assertThat(vector.z).isEqualTo(5)
    }
}

@DisplayName("Cuboid")
class CuboidTest {
    @Test
    fun `Constructor and Properties`() {
        val start = Vector3(1, 2, 3)
        val end = Vector3(4, 5, 6)
        val cuboid = Cuboid(start, end)

        assertThat(cuboid.start).isEqualTo(start)
        assertThat(cuboid.end).isEqualTo(end)
    }

    @Test
    fun `Addition with Vector3`() {
        val cuboid = Cuboid(Vector3(1, 2, 3), Vector3(4, 5, 6))
        assertThat(cuboid + Vector3(1, 0, -1)).isEqualTo(Cuboid(Vector3(2, 2, 2), Vector3(5, 5, 5)))
    }

    @Test
    fun `Subtraction with Vector3`() {
        val cuboid = Cuboid(Vector3(1, 2, 3), Vector3(4, 5, 6))
        assertThat(cuboid - Vector3(1, 0, -1)).isEqualTo(Cuboid(Vector3(0, 2, 4), Vector3(3, 5, 7)))
    }

    @Test
    fun Shift() {
        val cuboid = Cuboid(Vector3(1, 2, 3), Vector3(4, 5, 6))
        assertThat(cuboid.shift(z = -1)).isEqualTo(Cuboid(Vector3(1, 2, 2), Vector3(4, 5, 5)))
    }

    @Test
    fun Intersects() {
        val cuboid = Cuboid(Vector3(1, 1, 1), Vector3(2, 2, 2))
        assertThat(cuboid.intersects(Cuboid(Vector3(1, 1, 1), Vector3(2, 2, 2)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(1, 1, 1), Vector3(2, 2, 3)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(1, 1, 1), Vector3(2, 3, 2)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(1, 1, 1), Vector3(3, 2, 2)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(1, 1, 1), Vector3(3, 3, 3)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(2, 2, 2), Vector3(3, 3, 3)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(0, 0, 0), Vector3(1, 1, 1)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(0, 0, 0), Vector3(3, 3, 3)))).isTrue
        assertThat(cuboid.intersects(Cuboid(Vector3(0, 0, 0), Vector3(0, 0, 3)))).isFalse
        assertThat(cuboid.intersects(Cuboid(Vector3(3, 3, 3), Vector3(4, 4, 4)))).isFalse
    }
}
