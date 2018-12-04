package com.adventofcode.dec2019.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class FuelCalibratorTest {
    @Test
    fun `should compute fuel requirement`() {
        val computer = FuelCalibrator()
        assertEquals(2, computer.computeFuel(12))
        assertEquals(2, computer.computeFuel(14))
        assertEquals(654, computer.computeFuel(1969))
        assertEquals(33583, computer.computeFuel(100756))
    }

    @Test
    fun `should compute total fuel requirement`() {
        val computer = FuelCalibrator()
        assertEquals(34241, computer.computeTotalFuel(listOf("12", "14", "1969", "100756")))
    }

    @Test
    fun `should compute compund fuel requirement`() {
        val computer = FuelCalibrator()
        assertEquals(2, computer.computeCompoundFuel(12))
        assertEquals(966, computer.computeCompoundFuel(1969))
        assertEquals(50346, computer.computeCompoundFuel(100756))
    }

    @Test
    fun `should compute total compund fuel requirement`() {
        val computer = FuelCalibrator()
        assertEquals(51314, computer.computeTotalCompoundFuel(listOf("12", "1969", "100756")))
    }

    @Test
    fun `should compute real value`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day01/input.txt").readLines()
        val calibrator = FuelCalibrator()
        val result = calibrator.computeTotalFuel(input)
        assertEquals(3502510, result)
    }

    @Test
    fun `should compute real compound value`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day01/input.txt").readLines()
        val calibrator = FuelCalibrator()
        val result = calibrator.computeTotalCompoundFuel(input)
        assertEquals(5250885, result)
    }
}