package com.adventofcode.dec2020.day14

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MaskedProgramProcessorTest {

    @Test
    fun `should force 0 bits to memory`() {
        val processor = MaskedProgramProcessor()
        val input = listOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101"
        )
        processor.process(input)
        assertEquals(73, processor[8])
        assertEquals(101, processor[7])
    }

    @Test
    fun `should force 1 bits to memory`() {
        val processor = MaskedProgramProcessor()
        val input = listOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
        )
        processor.process(input)
        assertEquals(64, processor[8])
    }

    @Test
    fun `should write correct values to memory from actual program`() {
        val processor = MaskedProgramProcessor()
        processor.process(readInputAsList())
        assertEquals(10452688630537, processor.sumMemory())
    }

    @Test
    fun `should save a V2 program to memory`() {
        val processor = MaskedProgramProcessor()
        val input = listOf(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100"
        )
        processor.process(input, 2)
        assertEquals(100, processor[26])
        assertEquals(100, processor[27])
        assertEquals(100, processor[58])
        assertEquals(100, processor[59])
    }


    @Test
    fun `should write correct values to memory V2`() {
        val processor = MaskedProgramProcessor()
        processor.process(
            listOf(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1"
            ), 2
        )
        assertEquals(208, processor.sumMemory())
    }

    @Test
    fun `should write correct values to memory from actual program V2`() {
        val processor = MaskedProgramProcessor()
        processor.process(readInputAsList(), 2)
        assertEquals(2881082759597, processor.sumMemory())
    }
}