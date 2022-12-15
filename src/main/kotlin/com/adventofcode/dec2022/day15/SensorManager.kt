package com.adventofcode.dec2022.day15

import com.adventofcode.positioning.Position

class SensorManager(input: List<String>) {

    val sensors = input.map { Sensor(it) }

    private fun Sensor(input: String): Sensor {
        val sensorPosition = parsePosition(
            input.substring(10, input.indexOf(":"))
        )
        val beaconPosition = parsePosition(input.drop(input.lastIndexOf("x=")))
        return Sensor(sensorPosition, beaconPosition)
    }

    private fun parsePosition(input: String): Position {
        val (x, y) = input.split(", ")
        return Position(x.drop(2).toInt(), y.drop(2).toInt())
    }

    fun countCoveredPositions(y: Int): Int {
        val ranges = computeRanges(y)
        val covered = ranges.sumOf { it.last - it.first + 1 }
        val beacons = sensors.map { it.closestBeacon }.toSet().count { it.y == y }
        return covered - beacons
    }

    private fun getNotCoveredPosition(y: Int, filter: IntRange): Int? {
        val ranges = computeRanges(y)
        return if (ranges.size == 2) {
            ranges.sortedBy { it.first }[0].last + 1
        } else {
            null
        }
    }

    private fun computeRanges(y: Int): Range = sensors.fold(emptyList()) { ranges, sensor ->
        sensor.getCoveredPositions(y)?.let { inc ->
            ranges.addRange(inc)
        } ?: ranges
    }

    fun findTuningFrequency(range: IntRange): Long {
        val (x, y) = range.firstNotNullOf { y -> getNotCoveredPosition(y, range)?.let { it to y } }
        return x.toLong() * 4000000 + y
    }
}