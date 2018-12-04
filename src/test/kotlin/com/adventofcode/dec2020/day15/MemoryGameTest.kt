package com.adventofcode.dec2020.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MemoryGameTest {

    val input = "0,3,6"

    @Test
    fun `should compute first values`() {
        val memory = MemoryGame(input)
        assertEquals(0, memory.next())
        assertEquals(3, memory.next())
        assertEquals(6, memory.next())
    }

    @Test
    fun `should compute next values`() {
        val memory = MemoryGame(input)
        repeat(3) {
            memory.next()
        }
        assertEquals(0, memory.next())
        assertEquals(3, memory.next())
        assertEquals(3, memory.next())
        assertEquals(1, memory.next())
        assertEquals(0, memory.next())
        assertEquals(4, memory.next())
        assertEquals(0, memory.next())
    }

    @Test
    fun `should compute last values`() {
        val memory = MemoryGame(input)
        repeat(2019) {
            memory.next()
        }
        assertEquals(436, memory.next())
    }

    @Test
    fun `should compute last values with actual data`() {
        val memory = MemoryGame("0,1,5,10,3,12,19")
        repeat(2019) {
            memory.next()
        }
        assertEquals(1373, memory.next())
    }

    @Test
    fun `should compute very last values with actual data`() {
        val memory = MemoryGame("0,1,5,10,3,12,19")
        repeat(29999999) {
            memory.next()
        }
        assertEquals(112458, memory.next())
    }
}