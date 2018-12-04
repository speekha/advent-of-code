package com.adventofcode.dec2019.day19

import com.adventofcode.dec2019.intCode.intCodeRunner
import kotlinx.coroutines.channels.Channel

class BeamController(
        private val program: String
) {
    private val coords = Channel<Long>(10)

    suspend fun countAffectedPoints(xRange: IntRange, yRange: IntRange): Int =
            yRange.sumBy { y ->
                print("$y: ")
                xRange.sumBy { x ->
                    computePoint(x, y)
                }.also {
                    println()
                }
            }

    internal suspend fun computePoint(x: Int, y: Int): Int {
        val impact = Channel<Long>(10)
        val computer = intCodeRunner(program, coords, impact)
        computer.executeProgram()
        coords.send(x.toLong())
        coords.send(y.toLong())
        val result = impact.receive().toInt()
        print(if (result == 0) '.' else '#')
        return result
    }

    suspend fun findBeamWidth(distance: Int, compute: suspend (Int, Int) -> Int): IntRange {
        val bottomRow = (0..distance).map { compute(it, distance) }
        return bottomRow.indexOf(1)..bottomRow.lastIndexOf(1)
    }

    suspend fun findMinX(row: Int, xMin: Int = 0, compute: suspend (Int, Int) -> Int) =
            generateSequence(xMin) { it + 1 }
                    .first {
                        val computePoint = compute(it, row)
                        computePoint == 1
                    }

    suspend fun findClosestSquare(size: Int, yRange: IntRange, compute: suspend (Int, Int) -> Int): Pair<Int, Int> {
        var xMin = 0
        yRange.forEach { y ->
            if (y >= size) {
                xMin = findMinX(y + size - 1, xMin, compute)
                if (compute(xMin + size - 1, y) == 1) {
                    return xMin to y
                }
            }
        }
        error("Not found")
    }
}
