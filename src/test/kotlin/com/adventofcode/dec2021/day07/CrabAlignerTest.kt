package com.adventofcode.dec2021.day07

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CrabAlignerTest {

    val input = "16,1,2,0,4,2,7,1,2,14"

    @Test
    fun `should align crabs`() {
        val aligner = CrabAligner(input)
        assertEquals(listOf(16,1,2,0,4,2,7,1,2,14), aligner.positions)
    }

    @Test
    fun `should find optimal position for linear consumption`() {
        val aligner = CrabAligner(input)
        assertEquals(37, aligner.computeLinearFuelConsumption())
    }

    @Test
    fun `should find actual optimal position for linear consumption`() {
        val aligner = CrabAligner(actualInputList[0])
        assertEquals(341558, aligner.computeLinearFuelConsumption())
    }

    @Test
    fun `should find optimal position for quadratic consumption`() {
        val aligner = CrabAligner(input)
        assertEquals(168, aligner.computeQuadraticFuelConsumption())
    }

    @Test
    fun `should find actual optimal position for quadratic consumption`() {
        val aligner = CrabAligner(actualInputList[0])
        assertEquals(93214037, aligner.computeQuadraticFuelConsumption())
    }
}