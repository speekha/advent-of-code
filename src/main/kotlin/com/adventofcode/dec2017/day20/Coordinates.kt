package com.adventofcode.dec2017.day20

data class Coordinates(
        val x: Int,
        val y: Int,
        val z: Int) {
    companion object {
        fun parse(input: String): Coordinates {
            val (x, y, z) = input.split(",").map { it.trim().toInt() }
            return Coordinates(x, y, z)
        }
    }
}