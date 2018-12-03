package com.adventofcode.dec2017.day20

data class AxisState(val position: Int, val speed: Int, val acceleration: Int) {

    fun computeNextState(): AxisState {
        val speed = speed + acceleration
        return AxisState(position + speed, speed, acceleration)
    }

    fun computeFutureState(t: Int = 1) = AxisState(computeFuturePosition(t), computeFutureSpeed(t), acceleration)

    private fun computeFutureSpeed(t: Int = 1) = speed + t * acceleration

    private fun computeFuturePosition(t: Int = 1) = position + t * speed + t * (t + 1) * acceleration / 2

}