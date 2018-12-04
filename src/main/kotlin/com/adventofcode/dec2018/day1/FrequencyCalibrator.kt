package com.adventofcode.dec2018.day1

import java.io.File

class FrequencyCalibrator(input: List<String>) {

    fun calibrate(): Int = frequencies.sum()

    fun findDuplicateFrequency(): Int {
        val freq = HashSet<Int>()
        var i = 0
        var current = 0
        do {
            current += frequencies[i]
            i = (i + 1) % frequencies.size
            if (current in freq)
                return current
            freq += current
        } while (true)
    }

    val frequencies = input.map { it.toInt() }
}

fun main() {
    val input = File("src/main/kotlin/fr/adventofcode/day01/input.txt").readLines()
    val calibrator = FrequencyCalibrator(input)
    var result = calibrator.calibrate()
    println("Calibration: $result")
    result = calibrator.findDuplicateFrequency()
    println("First duplicate: $result")
}