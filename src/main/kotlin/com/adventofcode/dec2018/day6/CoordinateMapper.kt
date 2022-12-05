package com.adventofcode.dec2018.day6

import java.io.File

class CoordinateMapper(input: List<String>) {

    private val points = input.map {
        val (x, y) = it.split(", ")
        Position(x.toInt(), y.toInt())
    }

    val xMin: Int = points.minByOrNull { it.x }?.x ?: 0
    val xMax: Int = points.maxByOrNull { it.x }?.x ?: Integer.MAX_VALUE

    val yMin: Int = points.minByOrNull { it.y }?.y ?: 0
    val yMax: Int = points.maxByOrNull { it.y }?.y ?: Integer.MAX_VALUE

    fun findLargestFiniteArea(): Int {
        val plot = Array(xMax - xMin + 1) { x ->
            Array(yMax - yMin + 1) { y ->
                findClosestTo(Position(x, y))
            }
        }
        print(plot)

        val eligible = points.indices.toMutableList()
        (xMin..xMax).forEach { x ->
            eligible.remove(findClosestTo(Position(x, yMin - 1)))
            eligible.remove(findClosestTo(Position(x, yMax + 1)))
        }
        (yMin..yMax).forEach { y ->
            eligible.remove(findClosestTo(Position(xMin - 1, y)))
            eligible.remove(findClosestTo(Position(xMax + 1, y)))
        }

        return eligible.maxOfOrNull { center ->
            plot.sumOf { x -> x.count { y -> y == center } }
        } ?: 0
    }

    private fun findClosestTo(position: Position): Int {
        val groups = points.indices.groupBy { points[it] - position }
        val minKey = groups.minByOrNull { it.key } ?: error("No min")
        return if (minKey.value.size == 1) {
            minKey.value[0]
        } else {
            points.size
        }
    }

    operator fun <T : Any> Array<Array<T>>.get(pos: Position) = get(pos.x - xMin)[pos.y - yMin]

    operator fun <T : Any> Array<Array<T>>.set(pos: Position, value: T) {
        get(pos.x - xMin)[pos.y - yMin] = value
    }

    fun findLargestSafeArea(threshold: Int): Int {
        val plot = Array(xMax - xMin + 1) { x ->
            Array(yMax - yMin + 1) { y ->
                points.sumOf { it - Position(x, y) }
            }
        }

        return plot.sumOf { x -> x.count { y -> y < threshold } }
    }

}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day6/input.txt").readLines()
    val stats = CoordinateMapper(input)
    println("Largest finite area: ${stats.findLargestFiniteArea()}")
    println("Largest finite area: ${stats.findLargestSafeArea(10000)}")
}
