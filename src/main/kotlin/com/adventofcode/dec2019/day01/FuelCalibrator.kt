package com.adventofcode.dec2019.day01

import java.io.File

class FuelCalibrator {
    fun computeFuel(mass: Int): Int {
        return mass / 3 - 2
    }

    fun computeTotalFuel(listOf: List<String>): Int{
        return listOf.map { computeFuel(it.toInt()) }.sum()
    }

    fun computeCompoundFuel(mass: Int): Int {
        var total = 0
        var currentMass = mass
        var result: Int
        do {
            result = computeFuel(currentMass).coerceAtLeast(0)
            total += result
            currentMass = result
        } while(currentMass > 0)

        return total
    }

    fun computeTotalCompoundFuel(listOf: List<String>): Int {
        return listOf.map { computeCompoundFuel(it.toInt()) }.sum()
    }

}


fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2019/day01/input.txt").readLines()
    val calibrator = FuelCalibrator()
    var result = calibrator.computeTotalFuel(input)
    println("Required fuel: $result")
    result = calibrator.computeTotalCompoundFuel(input)
    println("Required fuel: $result")
}