package com.adventofcode.dec2015.day06

class LightController(
    private val process: Action.(Int) -> Int
) {

    private val lightBulbs: Array<IntArray> = Array(1000) { IntArray(1000) }

    fun execute(input: List<String>) {
        input.forEach {
            execute(it)
        }
    }

    fun execute(input: String) {
        val action = Action.values().first { input.startsWith(it.label) }
        val (xRange, yRange) = parseCoordinates(input.drop(action.label.length + 1))
        xRange.forEach { i ->
            yRange.forEach { j ->
                lightBulbs[i][j] = action.process(lightBulbs[i][j])
            }
        }
    }

    private fun parseCoordinates(coordinates: String): Pair<IntRange, IntRange> {
        val (from, to) = coordinates.split(" through ")
        val (xmin, ymin) = from.split(",").map { it.toInt() }
        val (xmax, ymax) = to.split(",").map { it.toInt() }
        return (xmin..xmax) to (ymin..ymax)
    }

    fun measureLuminosity(): Int = lightBulbs.sumOf { it.sum() }

    enum class Action(val label: String) {
        TURN_ON("turn on"), TURN_OFF("turn off"), TOGGLE("toggle")
    }
}

fun binaryLights() = LightController { value ->
    when (this) {
        LightController.Action.TOGGLE -> 1 - value
        LightController.Action.TURN_ON -> 1
        LightController.Action.TURN_OFF -> 0
    }
}

fun gradualLights() = LightController { value ->
    (value + when (this) {
        LightController.Action.TOGGLE -> 2
        LightController.Action.TURN_ON -> 1
        LightController.Action.TURN_OFF -> -1
    }).coerceAtLeast(0)
}