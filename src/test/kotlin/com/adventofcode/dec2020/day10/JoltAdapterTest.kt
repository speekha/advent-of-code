package com.adventofcode.dec2020.day10

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JoltAdapterTest {

    val input = listOf(
        "16",
        "10",
        "15",
        "5",
        "1",
        "11",
        "7",
        "19",
        "6",
        "12",
        "4"
    )

    @Test
    fun `should compute max jolt`() {
        val adapter = JoltAdapter(input)
        assertEquals(22, adapter.maxJolt)
    }

    @Test
    fun `should compute combined jolt difference`() {
        val adapter = JoltAdapter(input)
        assertEquals(35, adapter.computeCombinedDifferences())
    }

    @Test
    fun `should compute combined jolt difference in actual data`() {
        val adapter = JoltAdapter(readInputAsList())
        assertEquals(2030, adapter.computeCombinedDifferences())
    }

    @Test
    fun `should count possible arrangements`() {
        val adapter = JoltAdapter(input)
        assertEquals(8, adapter.countPossibleArrangements())
    }

    @Test
    fun `should count possible arrangements in actual data`() {
        val adapter = JoltAdapter(readInputAsList())
        assertEquals(42313823813632, adapter.countPossibleArrangements())
    }
}