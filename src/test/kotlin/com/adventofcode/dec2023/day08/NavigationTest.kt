package com.adventofcode.dec2023.day08

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NavigationTest {

    val input = listOf(
        "RL",
        "",
        "AAA = (BBB, CCC)",
        "BBB = (DDD, EEE)",
        "CCC = (ZZZ, GGG)",
        "DDD = (DDD, DDD)",
        "EEE = (EEE, EEE)",
        "GGG = (GGG, GGG)",
        "ZZZ = (ZZZ, ZZZ)"
    )

    @Test
    fun `should load the map`() {
        val navigator = Navigation(input)
        assertEquals("RL", navigator.path)
        assertEquals(7, navigator.network.size)
    }

    @Test
    fun `should count steps`() {
        var navigator = Navigation(input)
        assertEquals(2, navigator.countSteps())
        navigator = Navigation(
            listOf(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)"
            )
        )
        assertEquals(6, navigator.countSteps())
    }

    @Test
    fun `should count actual steps`() {
        var navigator = Navigation(actualInputList)
        assertEquals(16579, navigator.countSteps())
    }

    @Test
    fun `should count ghost steps`() {
        val navigator = Navigation(
            listOf(
        "LR",
        "",
        "11A = (11B, XXX)",
        "11B = (XXX, 11Z)",
        "11Z = (11B, XXX)",
        "22A = (22B, XXX)",
        "22B = (22C, 22C)",
        "22C = (22Z, 22Z)",
        "22Z = (22B, 22B)",
        "XXX = (XXX, XXX)"
            )
        )
        assertEquals(6, navigator.countGhostSteps())
    }

    @Test
    fun `should count actual ghost steps`() {
        val navigator = Navigation(actualInputList
        )
        assertEquals(12927600769609, navigator.countGhostSteps())
    }
}