package com.adventofcode.dec2021.day12

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CaveMapperTest {

    val input = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    )

    @Test
    fun `should load cave system`() {
        val mapper = CaveMapper(input)
        assertEquals(6, mapper.nodes.size)
    }

    @Test
    fun `should connect nodes`() {
        val mapper = CaveMapper(input)
        assertEquals(setOf("A", "b"), mapper.nodes["start"])
    }

    @Test
    fun `should count paths`() {
        val mapper = CaveMapper(input)
        assertEquals(10, mapper.countPaths())
    }

    @Test
    fun `should count actual paths`() {
        val mapper = CaveMapper(actualInputList)
        assertEquals(4549, mapper.countPaths())
    }

    @Test
    fun `should count longer paths`() {
        val mapper = CaveMapper(input)
        assertEquals(36, mapper.countPaths(true))
    }

    @Test
    fun `should count actual longer paths`() {
        val mapper = CaveMapper(actualInputList)
        assertEquals(120535, mapper.countPaths(true))
    }
}