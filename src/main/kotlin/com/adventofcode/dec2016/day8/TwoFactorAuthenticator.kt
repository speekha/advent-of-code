package com.adventofcode.dec2016.day8

import java.io.File

class TwoFactorAuthenticator(val width: Int, val height: Int) {

    var screen = Array(width) { BooleanArray(height) }

    fun parseInstructions(input: List<String>) {
        input.forEach { parseInstruction(it) }
    }

    fun parseInstruction(input: String): Int {
        val split = input.split(" ")
        when (split[0]) {
            "rect" -> {
                val (x, y, _) = split[1].split("x")
                drawRect(x.toInt(), y.toInt())
            }

            "rotate" -> {
                when (split[1]) {
                    "column" -> rotateColumn(split[2].substring(2).toInt(), split[4].toInt())
                    "row" -> rotateRow(split[2].substring(2).toInt(), split[4].toInt())
                }
            }
        }
        return countPixels()
    }

    fun drawRect(cols: Int, rows: Int) {
        for (x in 0 until cols) {
            for (y in 0 until rows) {
                screen[x][y] = true
            }
        }
    }

    fun rotateColumn(col: Int, offset: Int) {
        screen[col] = BooleanArray(height) {
            try {
                screen[col][(it - offset + height) % height]
            } catch (e: Throwable) {
                println("$offset $height")
                throw e
            }
        }
    }

    fun rotateRow(row: Int, offset: Int) {
        screen = Array(width) { x ->
            BooleanArray(screen[0].size) { y ->
                if (y == row)
                    screen[(x - offset + width) % width][y]
                else
                    screen[x][y]
            }
        }
    }

    fun countPixels() = screen.sumOf { it.count { it } }

    fun printDisplay() {
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                print(if (screen[x][y]) "#" else ".")
            }
            println()
        }
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day8/input.txt").readLines()
    with(TwoFactorAuthenticator(50, 6)) {
        parseInstructions(input)
        println("Final lit LCDs: ${countPixels()}")
        printDisplay()
    }
}