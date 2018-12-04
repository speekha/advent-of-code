package com.adventofcode.dec2018.day11

class FuelManager(val serialNumber: Int) {

    val size = 300

    val powerCells = (0 until size).map { x ->
        (0 until size).map { y ->
            getPowerLevel(x + 1, y + 1)
        }
    }

    fun getPower(x: Int, y: Int): Int = powerCells[x - 1][y - 1]

    private fun getPowerLevel(x: Int, y: Int): Int {
        val rackId = x + 10
        return ((rackId * y + serialNumber) * rackId / 100) % 10 - 5
    }

    fun findHighestPowerGroup(): Pair<Int, Int> {
        val sumAll = sumGrid(3)
        val (maxX, maxY) = findMax(sumAll)

        return maxX + 1 to maxY + 1
    }

    private fun sumGrid(windowSize: Int): List<List<Int>> {
        val sumX = powerCells.map { it.toList().windowed(windowSize) { list -> list.sum() } }
        return sumX.windowed(windowSize) { range ->
            range[0].indices.map { i -> range.sumBy { it[i] } }
        }
    }

    private fun findMax(compressedGrid: List<List<Int>>): Triple<Int, Int, Int> {
        var max = compressedGrid[0][0]
        var maxX = 0
        var maxY = 0

        compressedGrid.forEachIndexed { x, row ->
            row.forEachIndexed { y, cell ->
                if (cell > max) {
                    max = cell
                    maxX = x
                    maxY = y
                }
            }
        }

        return Triple(maxX, maxY, max)
    }

    fun findHighestVariablePowerGroup(): Triple<Int, Int, Int> {
        var (maxX, maxY, max) = findMax(powerCells)
        var maxSize = 1
        (2..size).forEach { size ->
            val grid = sumGrid(size)
            val (x, y, localMax) = findMax(grid)
            if (localMax > max) {
                max = localMax
                maxX = x
                maxY = y
                maxSize = size
                println("X: $x, Y: $y, Size : $size, Max: $max")
            }
        }

        return Triple(maxX + 1, maxY + 1, maxSize)
    }
}

fun main() {
    val manager = FuelManager(8772)
    println("Highest power group: ${manager.findHighestPowerGroup()}")
    println("Highest power group: ${manager.findHighestVariablePowerGroup()}")
}
