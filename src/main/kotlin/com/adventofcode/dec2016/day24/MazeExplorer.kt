package com.adventofcode.dec2016.day24

class MazeExplorer(val input: List<String>) {

    val distances: Array<Array<Int>>

    val size = input.flatMap { it.asIterable().filter { it != '.' && it != '#' } }.size

    val pointsOfInterest = (0..size).map { index ->
        val y = input.indexOfFirst { it.contains('0' + index) }
        val x = input[y].indexOf('0' + index)
        x to y
    }

    init {
        distances = Array(size) { Array(size) { 0 } }
        initDistance(input)
    }

    private fun initDistance(input: List<String>) {
        val maze = input.map { it.toByteArray() }
        for (i in pointsOfInterest.indices) {
            val (x0, y0) = pointsOfInterest[i]
            val queue = listOf("")

        }


    }

    fun findShortestRoute() = 14

}
