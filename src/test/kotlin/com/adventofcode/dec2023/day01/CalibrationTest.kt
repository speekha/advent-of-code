package com.adventofcode.dec2023.day01

import com.adventofcode.actualInputList
import kotlin.test.Test
import kotlin.test.assertEquals

class CalibrationTest {

    val input1 = listOf(
        "1abc2",
        "pqr3stu8vwx",
        "a1b2c3d4e5f",
        "treb7uchet"
    )

    val input2 = listOf(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
    )

    @Test
    fun `should compute calibration value`() {
        val calibrator = Calibrator()
        assertEquals(142, calibrator.calibrate(input1))
    }

    @Test
    fun `should compute actual calibration value`() {
        val calibrator = Calibrator()
        assertEquals(54634, calibrator.calibrate(actualInputList))
    }


    @Test
    fun `should compute corrected calibration value`() {
        val calibrator = Calibrator()
        assertEquals(281, calibrator.advancedCalibration(input2))
    }
    @Test
    fun `should compute actual corrected calibration value`() {
        val calibrator = Calibrator()
        // > 53846
        assertEquals(1, calibrator.advancedCalibration(actualInputList))
    }
}