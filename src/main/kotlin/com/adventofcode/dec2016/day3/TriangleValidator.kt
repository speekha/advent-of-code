package com.adventofcode.dec2016.day3

import java.io.File

class TriangleValidator {
    fun isValidTriangle(input: String): Boolean {
        val sides = input.trim().split(Regex(" +")).map { it.trim().toInt() }
        return ((0..2).none { sides[it] >= sides[(it + 1) % 3] + sides[(it + 2) % 3] })
    }

    fun isValidTriangle(vararg sides: Int): Boolean {
        return ((0..2).none { sides[it] >= sides[(it + 1) % 3] + sides[(it + 2) % 3] })
    }

    fun countValidTriangles(input: List<String>): Int = input.count { isValidTriangle(it) }

    fun countValidTriangles(input: String): Int {
        val rows = input.split("\n")
        var total = 0
        for (i in 0 until rows.size step 3) {
            val side1 = rows[i].trim().split(Regex(" +")).map(String::toInt)
            val side2 = rows[i+1].trim().split(Regex(" +")).map(String::toInt)
            val side3 = rows[i+2].trim().split(Regex(" +")).map(String::toInt)
            if (isValidTriangle(side1[0], side2[0], side3[0])) total++
            if (isValidTriangle(side1[1], side2[1], side3[1])) total++
            if (isValidTriangle(side1[2], side2[2], side3[2])) total++
        }
        return total
    }
}

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day3/input.txt").readLines()
    println("Testing : ${input.size}")
    with(TriangleValidator()) {
        println("Valid triangles: ${countValidTriangles(input)}")
        println("Valid triangles: ${countValidTriangles(input.joinToString("\n"))}")
    }
}