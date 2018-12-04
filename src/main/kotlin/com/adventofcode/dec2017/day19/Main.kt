package com.adventofcode.dec2017.day19

import java.io.File

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day19/input.txt").readLines()
    with(PacketRouter(input)) {
        val path = traverse()
        println("Path: ${path.first} in ${path.second} steps")
    }
}