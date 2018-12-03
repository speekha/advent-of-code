package com.adventofcode.dec2017.day13

import org.junit.Assert.*
import org.junit.Test

class PacketSmugglerTest {

        val input = listOf("0: 3", 
                "1: 2", 
                "4: 4", 
                "6: 4")
                
    @Test
    fun `should analyze network`() {
        val smuggler = PacketSmuggler(input)
        assertEquals(3, smuggler.getRange(0))
        assertEquals(2, smuggler.getRange(1))
        assertEquals(0, smuggler.getRange(2))
        assertEquals(0, smuggler.getRange(3))
        assertEquals(4, smuggler.getRange(4))
        assertEquals(0, smuggler.getRange(5))
        assertEquals(4, smuggler.getRange(6))
    }

    @Test
    fun `should detect scanners`() {
        val smuggler = PacketSmuggler(input)
        assertTrue(smuggler.isScannerAtTop(0, 0))
        assertFalse(smuggler.isScannerAtTop(0, 1))
        assertFalse(smuggler.isScannerAtTop(0, 2))
        assertFalse(smuggler.isScannerAtTop(0, 3))
        assertTrue(smuggler.isScannerAtTop(0, 4))
    }

    @Test
    fun `should get caught twice`() {
        val smuggler = PacketSmuggler(input)
        assertEquals(listOf(0, 6), smuggler.traverse())
    }

    @Test
    fun `severity should be 24`() {
        val smuggler = PacketSmuggler(input)
        assertEquals(24, smuggler.computeSeverity())
    }

    @Test
    fun `delay should be 10`() {
        val smuggler = PacketSmuggler(input)
        assertEquals(10, smuggler.avoidScanners())
    }
}