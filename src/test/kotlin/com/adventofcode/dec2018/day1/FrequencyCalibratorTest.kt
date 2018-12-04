package com.adventofcode.dec2018.day1

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FrequencyCalibratorTest {

    val input = "+1, -2, +3, +1".split(", ")

    @Test
    fun `should find duplicates`() {
        val calibrator = FrequencyCalibrator(input)
        assertEquals(2, calibrator.findDuplicateFrequency())
    }

    @Test
    fun `should calibrate`() {
        val input = readInputAsList()
        val calibrator = FrequencyCalibrator(input)
        assertEquals(505, calibrator.calibrate())
    }

    @Test
    fun `should find actual duplicates`() {
        val input = readInputAsList()
        val calibrator = FrequencyCalibrator(input)
        assertEquals(72330, calibrator.findDuplicateFrequency())
    }

}