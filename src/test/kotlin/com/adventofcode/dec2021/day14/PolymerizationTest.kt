package com.adventofcode.dec2021.day14

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PolymerizationTest {

    val input = listOf(
        "NNCB",
        "",
        "CH -> B",
        "HH -> N",
        "CB -> H",
        "NH -> C",
        "HB -> C",
        "HC -> B",
        "HN -> C",
        "NN -> C",
        "BH -> H",
        "NC -> B",
        "NB -> B",
        "BN -> B",
        "BB -> N",
        "BC -> B",
        "CC -> N",
        "CN -> C"
    )

    @Test
    fun `should load data`() {
        val polymerization = Polymerization(input)
        assertEquals(mapOf("NN" to 1L, "NC" to 1L, "CB" to 1L), polymerization.polymer)
        assertEquals("B", polymerization.rules["HC"])
        assertEquals(1, polymerization.computeScore())
    }

    @Test
    fun `should compute next polymer`() {
        val polymerization = Polymerization(input)
        polymerization.polymerize()
        assertEquals(
            mapOf("NC" to 1L, "CN" to 1L, "NB" to 1L, "BC" to 1L, "CH" to 1L, "HB" to 1L),
            polymerization.polymer
        )
        assertEquals(1, polymerization.computeScore())
    }

    @Test
    fun `should compute polymer score`() {
        val polymerization = Polymerization(input)
        repeat(10) {
            polymerization.polymerize()
        }
        assertEquals(1588, polymerization.computeScore())
        repeat(30) {
            polymerization.polymerize()
        }
        assertEquals(2188189693529, polymerization.computeScore())
    }

    @Test
    fun `should compute actual polymer score`() {
        val polymerization = Polymerization(actualInputList)
        repeat(10) {
            polymerization.polymerize()
        }
        assertEquals(2621, polymerization.computeScore())
        repeat(30) {
            polymerization.polymerize()
        }
        assertEquals(2843834241366, polymerization.computeScore())
    }
}