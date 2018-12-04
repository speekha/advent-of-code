package com.adventofcode.dec2021.day17

data class Probe(
    var x: Int = 0,
    var y: Int = 0,
    var velX: Int = 0,
    var velY: Int = 0
) {
    fun advance() {
        x += velX
        y += velY
        velX = (velX - 1).coerceAtLeast(0)
        velY -= 1
    }
}
