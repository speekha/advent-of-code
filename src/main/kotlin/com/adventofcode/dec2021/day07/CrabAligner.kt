package com.adventofcode.dec2021.day07

import kotlin.math.abs

class CrabAligner(input: String) {

    val positions: List<Int> = input.split(",").map { it.toInt() }

    private fun getRange() = (positions.minOrNull()?:0)..(positions.maxOrNull()?:0)

    fun computeLinearFuelConsumption(): Int = getRange().minOf { position ->
        positions.sumOf { abs(position - it) }
    }

    fun computeQuadraticFuelConsumption(): Int = getRange().minOf { position ->
        positions.sumOf {
            val d = abs(position - it)
            d * (d + 1) / 2
        }
    }
}
