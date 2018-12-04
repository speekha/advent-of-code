package com.adventofcode.dec2016.day18

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TrapFinderTest {

    @Test
    fun `should parse row`() {
        val input = "..^^."
        val finder = TrapFinder()
        val row = finder.getRow(input)
        assertFalse(row.isTrapped(0))
        assertFalse(row.isTrapped(1))
        assertTrue(row.isTrapped(2))
        assertTrue(row.isTrapped(3))
        assertFalse(row.isTrapped(4))
    }

    @Test
    fun `should compute next row`() {
        val input = "..^^."
        val finder = TrapFinder()
        val row = finder.getRow(input)

        assertEquals(".^^^^", row.nextRow().toString())
        assertEquals("^^..^", row.nextRow().nextRow().toString())
    }

    @Test
    fun `should map larger grid correctly`() {
        val finder = TrapFinder()
        val map = listOf(".^^.^.^^^^",
                "^^^...^..^" ,
                "^.^^.^.^^." ,
                "..^^...^^^" ,
                ".^^^^.^^.^" ,
                "^^..^.^^.." ,
                "^^^^..^^^." ,
                "^..^^^^.^^" ,
                ".^^^..^.^^" ,
                "^^.^^^..^^")
        map.drop(1).forEachIndexed { index, row ->
            assertEquals(row, finder.getRow(map[index]).nextRow().toString())
        }
    }

    @Test
    fun `should count safe tiles`() {
        val finder = TrapFinder()
        assertEquals(38, finder.countSafeTiles(".^^.^.^^^^", 10))
    }

    @Test
    fun `process actual data`() {
        val finder = TrapFinder()
        val input = "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."
        assertEquals(2016, finder.countSafeTiles(input, 40))
        assertEquals(19998750, finder.countSafeTiles(input, 400000))
    }
}