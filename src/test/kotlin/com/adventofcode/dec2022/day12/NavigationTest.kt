package com.adventofcode.dec2022.day12

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NavigationTest {

    val input = listOf("Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi")

    @Test
    fun `should reach destination`() {
        val navigator = Navigation(input)
        assertEquals(31, navigator.computeRoute())
    }
    @Test
    fun `should reach actual destination`() {
        val navigator = Navigation(actualInputList)
        assertEquals(370, navigator.computeRoute())
    }

    @Test
    fun `should optimize route`() {
        val navigator = Navigation(input)
        assertEquals(29, navigator.computeRoute(optimize = true))
    }
    @Test
    fun `should optimize actual route`() {
        val navigator = Navigation(actualInputList)
        assertEquals(363, navigator.computeRoute(optimize = true))
    }
}