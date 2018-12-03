package com.adventofcode.dec2017.day24

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class BridgeBuilderTest {

    val input = listOf("0/2",
            "2/2",
            "2/3",
            "3/4",
            "3/5",
            "0/1",
            "10/1",
            "9/10")

    @Test
    fun `should parse input`() {
        val builder = BridgeBuilder(input)
        assertEquals(8, builder.components.size)
    }

    @Test
    fun `should find possible moves`() {
        val builder = BridgeBuilder(input)
        assertEquals(setOf(0 to 1, 0 to 2), builder.findPossibleMoves(0, builder.components).toSet())
    }

    @Test
    fun `strongest should be 31`() = with(BridgeBuilder(input)) {
        assertEquals(31, computeBridgeScore(findStrongestBridge()))
    }

    @Test
    fun `test real strongest`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day24/input.txt").readLines()

        with(BridgeBuilder(input)) {
            assertEquals(2006, computeBridgeScore(findStrongestBridge()))
        }
    }

    @Test
    fun `strength of longest should be 19`() = with(BridgeBuilder(input)) {
        assertEquals(19, computeBridgeScore(findLongestBridge()))
    }

    @Test
    fun `test real longest`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day24/input.txt").readLines()

        with(BridgeBuilder(input)) {
            assertEquals(1994, computeBridgeScore(findLongestBridge()))
        }
    }
}