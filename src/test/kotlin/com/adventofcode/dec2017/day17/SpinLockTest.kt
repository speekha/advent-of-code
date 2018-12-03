package com.adventofcode.dec2017.day17

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class SpinLockTest {
    val input = listOf(
            "0",
            "0 1",
            "0 2 1",
            "0 2 3 1",
            "0 2 4 3 1",
            "0 5 2 4 3 1",
            "0 5 2 4 3 6 1",
            "0 5 7 2 4 3 6 1",
            "0 5 7 2 4 3 8 6 1",
            "0 9 5 7 2 4 3 8 6 1")

    @Test
    fun `should convert to list`() {
        val list = Node(4)
        list.insert(2).insert(3).insert(1)
        assertEquals(listOf(4, 2, 3, 1), list.toList())
    }

    @Test
    fun `should convert to string`() {
        val list = Node(4)
        list.insert(2).insert(3).insert(1)
        assertEquals("4 2 3 1", list.toString())
    }

    @Test
    fun `should match input`() {
        input.indices.forEach {
            val spin = SpinLock(3)
            spin.iterate(it)
            assertEquals(input[it], spin.getState())
        }
    }

    @Test
    fun `should contain final steps`() {
        val input = "1512 1134 151 2017 638 1513 851"
        val spin = SpinLock(3)
        spin.iterate(2017)
        assertTrue(spin.getState().contains(input))
    }

    @Test
    fun `short-circuit value should be 638`() {
        val spin = SpinLock(3)
        assertEquals(638, spin.shortCircuit(2017))
    }

    @Test
    fun `should be 926`() {
        val spin = SpinLock(394)
        assertEquals(926, spin.shortCircuit(2017))
    }

    @Test
    fun `should be 9`() {
        assertEquals(9, SpinLock(3).getPostZeroEntry(9))
    }

    @Ignore
    @Test
    fun `should be 10150888`() {
        assertEquals(10150888, SpinLock(394).getPostZeroEntry(50000000))
    }
}