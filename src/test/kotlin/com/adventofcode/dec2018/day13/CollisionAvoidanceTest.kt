package com.adventofcode.dec2018.day13

import com.adventofcode.dec2018.day13.Direction.*
import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CollisionAvoidanceTest {

    val input = listOf(
            "/->-\\        ",
            "|   |  /----\\",
            "| /-+--+-\\  |",
            "| | |  | v  |",
            "\\-+-/  \\-+--/",
            "  \\------/   ")

    @Test
    fun `should parse map`() {
        val avoider = CollisionAvoidance(input)
        assertEquals("/---\\        ", avoider.map[0].joinToString(""))
        assertEquals(listOf(Cart(0, Position(2, 0), RIGHT), Cart(1, Position(9, 3), DOWN)), avoider.carts)
    }

    @Test
    fun `cart should move correctly`() {
        val position = Position(1, 1)
        assertEquals(Position(1, 0), position + UP)
        assertEquals(Position(2, 1), position + RIGHT)
        assertEquals(Position(1, 2), position + DOWN)
        assertEquals(Position(0, 1), position + LEFT)
    }

    @Test
    fun `should handle turns`() {
        assertEquals(RIGHT, UP + '/')
        assertEquals(LEFT, UP + '\\')
        assertEquals(UP, UP + '|')
    }

    @Test
    fun `should process one tick`() {
        val nextState = "/-->\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\->--/\n" +
                "  \\------/   "
        val avoider = CollisionAvoidance(input)
        avoider.iterate()
        assertEquals(nextState, avoider.state)
    }

    @Test
    fun `should process intersections`() {
        val nextState = "/---\\        \n" +
                "|   |  /----<\n" +
                "| /-+-->-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/   "
        val avoider = CollisionAvoidance(input)
        repeat(7) {
            avoider.iterate()
        }
        assertEquals(nextState, avoider.state)
    }

    @Test
    fun `should process 13 steps`() {
        val nextState = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--v-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  ^-+--/\n" +
                "  \\------/   "
        val avoider = CollisionAvoidance(input)
        repeat(13) {
            avoider.iterate()
        }
        assertEquals(nextState, avoider.state)
    }

    @Test
    fun `should find crash`() {
        val avoider = CollisionAvoidance(input)
        try {
            repeat(14) {
                avoider.iterate()
            }
            fail<Unit>("Should have crashed")
        } catch (e: CartCrashException) {
            assertEquals(Position(7, 3), e.position)
        }
    }

    @Test
    fun `should find actual crash`() {
        val input = readInputAsList()
        val avoider = CollisionAvoidance(input)
        try {
            repeat(148) {
                avoider.iterate()
            }
            fail<Unit>("Should have crashed")
        } catch (e: CartCrashException) {
            assertEquals(Position(113, 136), e.position)
        }
    }


    @Test
    fun `should find last remaining cart`() {
        val input = listOf("/>-<\\  ",
                "|   |  ",
                "| /<+-\\",
                "| | | v",
                "\\>+</ |",
                "  |   ^",
                "  \\<->/")
        val avoider = CollisionAvoidance(input)
        repeat(3) {
            avoider.iterate(true)
        }
        assertEquals(Position(6, 4), avoider.carts.first { it.position != NULL_POSITION }.position)
    }

    @Test
    fun `should find actual last cart`() {
        val input = readInputAsList()
        val avoider = CollisionAvoidance(input)
        while (avoider.carts.count { it.position != NULL_POSITION } > 1) {
            avoider.iterate(true)
        }
        assertNotEquals(Position(72, 146), avoider.carts.first { it.position != NULL_POSITION }.position)
        assertNotEquals(Position(73, 146), avoider.carts.first { it.position != NULL_POSITION }.position)
    }
}