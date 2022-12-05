package com.adventofcode.dec2018.day3

import java.io.File

class FabricMapper(input: List<String>) {

    val claims = mapClaims(input)
    val width: Int
        get() = claims.map { it.left + it.width + 1 }.maxOrNull() ?: 0
    val height: Int
        get() = claims.map { it.top + it.height + 1 }.maxOrNull() ?: 0
    val map = Array(width) { Array(height) { 0 } }.also { map ->
        claims.forEach {
            (it.left until it.left + it.width).forEach { x ->
                (it.top until it.top + it.height).forEach { y ->
                    map[x][y]++
                }
            }
        }
    }

    val overlap: Int
        get() = map.sumOf { it.count { it > 1 } }


    private fun mapClaims(input: List<String>): List<Claim> {
        return input.map {
            val (id, _, position, size) = it.split(" ")
            val (left, top) = position.dropLast(1).split(",")
            val (width, height) = size.split("x")

            Claim(id, left.toInt(), top.toInt(), width.toInt(), height.toInt())
        }
    }

    fun findNonOverLappingClaim(): String = claims.first {
        (it.left until it.left + it.width).all { x -> (it.top until it.top + it.height).all { y -> map[x][y] == 1 } }
    }.id
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day3/input.txt").readLines()
    val mapper = FabricMapper(input)
    println("Overlaps: ${mapper.overlap}")
    println("Non overlapping claim: ${mapper.findNonOverLappingClaim()}")
}