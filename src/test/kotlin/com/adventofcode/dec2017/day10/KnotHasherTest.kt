package com.adventofcode.dec2017.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KnotHasherTest {

    @Test
    fun `process first step`() {
        val hasher = KnotHasher(5)
        hasher.processList(intArrayOf(3))
        assertEquals("2 1 0 3 4", hasher.string.joinToString(" "))
    }

    @Test
    fun `process second step`() {
        val hasher = KnotHasher(5)
        hasher.processList(intArrayOf(3, 4))
        assertEquals("4 3 0 1 2", hasher.string.joinToString(" "))
    }

    @Test
    fun `process third step`() {
        val hasher = KnotHasher(5)
        hasher.processList(intArrayOf(3, 4, 1))
        assertEquals("4 3 0 1 2", hasher.string.joinToString(" "))
    }

    @Test
    fun `process fourth step`() {
        val hasher = KnotHasher(5)
        hasher.processList(intArrayOf(3, 4, 1, 5))
        assertEquals("3 4 2 1 0", hasher.string.joinToString(" "))
    }

    @Test
    fun `hash should be 12`() {
        assertEquals(12, KnotHasher(5).simpleHash(intArrayOf(3, 4, 1, 5)))
    }

    @Test
    fun `test dense hash`() {
        val hasher = KnotHasher(256)
        (0..255).forEach { hasher.string[it] = if (it % 16 == 0) 1 else 0 }
        var hash = hasher.denseHash()
        hash.forEach { assertEquals(1, it) }
        (0..255).forEach { hasher.string[it] = 1}
        hash = hasher.denseHash()
        hash.forEach { assertEquals(0, it) }
        (0..255).forEach { hasher.string[it] = if (it % 8 == 0) 1 else 0 }
        hash = hasher.denseHash()
        hash.forEach { assertEquals(0, it) }
    }

    @Test
    fun `first dense hash should be 64`() {
        val hasher = KnotHasher(256)
        val input = arrayOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)
        val reduce = input.reduce { a, b -> a xor b }
        input.indices.forEach { hasher.string[it] = input[it] }
        val hash = hasher.denseHash()
        assertEquals(64, reduce)
        assertEquals(64, hash[0])
    }

    @Test
    fun `test empty String`() {
        val hash = "a2582a3a0e66e6e86e3812dcb672a272"
        assertEquals(hash, KnotHasher(256, 64).digestToString(""))
    }

    @Test
    fun `test AoC 2017`() {
        val hash = "33efeb34ea91902bb2f59c9920caa6cd"
        assertEquals(hash, KnotHasher(256, 64).digestToString("AoC 2017"))
    }

    @Test
    fun `test 1,2,3`() {
        val hash = "3efbe78a8d82f29979031a4aa0b16a9d"
        assertEquals(hash, KnotHasher(256, 64).digestToString("1,2,3"))
    }

    @Test
    fun `test 1,2,4`() {
        val hash = "63960835bcdc130f0b66d7ff4f6a5a8e"
        assertEquals(hash, KnotHasher(256, 64).digestToString("1,2,4"))
    }
}