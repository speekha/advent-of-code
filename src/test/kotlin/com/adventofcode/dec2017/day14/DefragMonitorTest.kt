package com.adventofcode.dec2017.day14

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class DefragMonitorTest {

    val input = "flqrgnkx"

    val output = listOf(
            "##.#.#..",
            ".#.#.#.#",
            "....#.#.",
            "#.#.##.#",
            ".##.#...",
            "##..#..#",
            ".#...#..",
            "##.#.##.")

    @Test
    fun `hash should be a list of 16 integers`() {
        val defrag = DefragMonitor(input)
        output.indices.forEach {
            val hash = defrag.computeRow(it)
            assertEquals(16, hash.size)
        }
    }

    @Test
    fun `digest should be 128 chars`() {
        with(DefragMonitor(input)) {
            output.indices.forEach {
                val hash1 = displayRow(it)
                assertEquals(128, hash1.length)
            }
        }
    }

    @Test
    fun `should match output`() {
        val defrag = DefragMonitor(input)
        output.indices.forEach {
            val hash = defrag.displayRow(it).map { if (it == '1') '#' else '.' }.joinToString("")
            assertThat("Line #$it", hash, CoreMatchers.startsWith(output[it]))
        }
    }

    @Test
    fun `should be 8108`() {
        val defrag = DefragMonitor(input)
        assertEquals(8108, defrag.countUsedSquares())
    }

    @Test
    fun `should be 12 regions`() {
        val defrag = DefragMonitor(input)
        val rows = output.map { row ->
            BooleanArray(row.length) { row[it] == '#' }
        }.toTypedArray()
        assertEquals(12, defrag.countRegions(rows))
    }

    @Test
    fun `should be 1242`() {
        val defrag = DefragMonitor(input)
        assertEquals(1242, defrag.countRegions())
    }
}