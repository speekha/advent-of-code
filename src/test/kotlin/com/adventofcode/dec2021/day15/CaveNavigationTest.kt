package com.adventofcode.dec2021.day15

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CaveNavigationTest {

    val input = listOf(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581")

    @Test
    fun `should compute lowest risk path`() {
        val navigation = CaveNavigation(input)
        assertEquals(40, navigation.lowestRiskRoute())
    }

    @Test
    fun `should compute lowest risk path in bigger cave`() {
        val navigation = CaveNavigation(input)
        assertEquals(315, navigation.lowestRiskRoute(5))
    }

    @Test
    fun `should compute actual lowest risk path`() {
        val navigation = CaveNavigation(actualInputList)
        assertEquals(429, navigation.lowestRiskRoute())
    }

    @Test
    fun `should compute actual lowest risk path in bigger cave`() {
        val navigation = CaveNavigation(actualInputList)
        assertEquals(2844, navigation.lowestRiskRoute(5))
    }
}