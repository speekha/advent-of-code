package com.adventofcode.dec2022.day15

import com.adventofcode.positioning.Position
import kotlin.math.abs

data class Sensor(
    val position: Position,
    val closestBeacon: Position
) {
    fun getCoveredPositions(y: Int): IntRange? {
        val fullRange = position.distance(closestBeacon)
        val yDistance = position.distance(Position(position.x, y))
        return if (yDistance < fullRange) {
            val xRange = fullRange - yDistance
            position.x - xRange.. position.x + xRange
        } else {
            null
        }
    }
}