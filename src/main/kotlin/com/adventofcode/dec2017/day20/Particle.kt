package com.adventofcode.dec2017.day20

import java.util.regex.Matcher
import java.util.regex.Pattern

data class Particle(
        val x: AxisState,
        val y: AxisState,
        val z: AxisState
) {
    constructor(position: Coordinates, speed: Coordinates, acceleration: Coordinates) : this(
            AxisState(position.x, speed.x, acceleration.x),
            AxisState(position.y, speed.y, acceleration.y),
            AxisState(position.z, speed.z, acceleration.z)
    )

    val distance: Int
        get() = x.position + y.position + z.position

    fun computeNextPosition(): Particle = Particle(x.computeNextState(), y.computeNextState(), z.computeNextState())

    fun computePosition(t: Int = 1): Particle =
            Particle(x.computeFutureState(t), y.computeFutureState(t), z.computeFutureState(t))

    companion object {
        val pattern = Pattern.compile("<[^>]+>")

        fun parse(input: String): Particle {
            val matcher = pattern.matcher(input)
            val position = Coordinates.parse(input.substring(matcher.range()))
            val speed = Coordinates.parse(input.substring(matcher.range(matcher.end())))
            val acceleration = Coordinates.parse(input.substring(matcher.range(matcher.end())))
            return Particle(position, speed, acceleration)
        }

        private fun Matcher.range(offset: Int = 0): IntRange {
            find(offset)
            return start() + 1 until end() - 1
        }
    }
}