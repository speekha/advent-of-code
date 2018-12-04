package com.adventofcode.dec2018.day10

import java.io.File

class MessageReader(input: List<String>) {

    val initialPositions: List<SkyPoint> = input.map { parseRow(it) }

    var xMin: Int = 0
    var xMax: Int = 0
    var yMin: Int = 0
    var yMax: Int = 0

    init {
        updateFrame()
    }

    data class SkyPoint(var x: Int, var y: Int, val dX: Int, val dY: Int)

    fun parseRow(input: String): SkyPoint {
        val positionStart = input.indexOf('<') + 1
        val positionEnd = input.indexOf('>') - 1
        val velocityStart = input.indexOf('<', positionEnd) + 1
        val velocityEnd = input.indexOf('>', velocityStart) - 1
        val (x, y) = input.subSequence(positionStart..positionEnd).split(",").map { it.trim().toInt() }
        val (dx, dy) = input.subSequence(velocityStart..velocityEnd).split(",").map { it.trim().toInt() }
        return SkyPoint(x, y, dx, dy)
    }

    fun iterate() {
        initialPositions.forEach {
            it.x += it.dX
            it.y += it.dY
        }
    }

    fun reverse() {
        initialPositions.forEach {
            it.x -= it.dX
            it.y -= it.dY
        }
    }

    fun renderState(): String {
        val screen = Array(yMax - yMin + 1) {
            CharArray(xMax - xMin + 1) {
                '.'
            }
        }
        initialPositions.forEach { screen[it.y - yMin][it.x - xMin] = '#' }
        return screen.joinToString("\n") {
            it.joinToString("")
        }
    }

    fun waitForMessage(): Pair<Int, String> {
        var newRangeX = xMax - xMin
        var newRangeY = yMax - yMin
        var rangeX: Int
        var rangeY: Int
        var t = 0
        do {
            rangeX = newRangeX
            rangeY = newRangeY
            iterate()
            t++
            updateFrame()
            newRangeX = xMax - xMin
            newRangeY = yMax - yMin
        } while (newRangeX < rangeX && newRangeY < rangeY)
        reverse()
        updateFrame()
        t--
        return t to renderState()
    }

    private fun updateFrame() {
        xMin = initialPositions.map { it.x }.minOrNull() ?: 0
        xMax = initialPositions.map { it.x }.maxOrNull() ?: 0
        yMin = initialPositions.map { it.y }.minOrNull() ?: 0
        yMax = initialPositions.map { it.y }.maxOrNull() ?: 0
    }
}


fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day10/input.txt").readLines()
    val reader = MessageReader(input)
    val result = reader.waitForMessage()
    println("Message et ${result.first}s: \n${result.second.replace('.', ' ')}")
}