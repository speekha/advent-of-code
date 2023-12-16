package com.adventofcode.dec2023.day16

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BeamTracerTest {

    val input = listOf(
        ".|...\\....",
        "|.-.\\.....",
        ".....|-...",
        "........|.",
        "..........",
        ".........\\",
        "..../.\\\\..",
        ".-.-/..|..",
        ".|....-|.\\",
        "..//.|...."
    )

    @Test
    fun `should trace beams`() {
        val tracer = BeamTracer(input)
        assertEquals(46, tracer.countEnergizedTiles())
    }
    @Test
    fun `should trace actual beams`() {
        val tracer = BeamTracer(actualInputList)
        assertEquals(6605, tracer.countEnergizedTiles())
    }


    @Test
    fun `should optimize energy`() {
        val tracer = BeamTracer(input)
        assertEquals(51, tracer.optimizeEnergy())
    }

    @Test
    fun `should optimize actual energy`() {
        val tracer = BeamTracer(actualInputList)
        assertEquals(6766, tracer.optimizeEnergy())
    }
}