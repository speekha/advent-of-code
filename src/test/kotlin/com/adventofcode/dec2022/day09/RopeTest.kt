package com.adventofcode.dec2022.day09

import com.adventofcode.actualInputList
import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RopeTest {

    
    private val input = listOf("R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2")
    @Test
    fun `should move tail following head`() {
        val rope = Rope()
        assertEquals(Position(0, 0), rope.tail)
        rope.moveHead(Direction.RIGHT)
        assertEquals(Position(0, 0), rope.tail)
        rope.moveHead(Direction.RIGHT, 3)
        assertEquals(Position(3, 0), rope.tail)
        rope.moveHead(Direction.UP)
        assertEquals(Position(3, 0), rope.tail)
        rope.moveHead(Direction.UP)
        assertEquals(Position(4, -1), rope.tail)
    }

    @Test
    fun `should parse instructions`() {
        val rope = Rope()
        rope.moveHead(input)
        assertEquals(Position(1, -2), rope.tail)
    }
    @Test
    fun `should count positions`() {
        val rope = Rope()
        rope.moveHead(input)
        assertEquals(13, rope.tailPositions.size)
    }

    @Test
    fun `should count actual positions`() {
        val rope = Rope()
        rope.moveHead(actualInputList)
        assertEquals(5779, rope.tailPositions.size)
    }

    @Test
    fun `should count positions with longer rope`() {
        val rope = Rope(10)
        rope.moveHead(listOf("R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20"))
        assertEquals(36, rope.tailPositions.size)
    }

    @Test
    fun `should count actual positions with longer rope`() {
        val rope = Rope(10)
        rope.moveHead(actualInputList)
        assertEquals(2331, rope.tailPositions.size)
    }
}