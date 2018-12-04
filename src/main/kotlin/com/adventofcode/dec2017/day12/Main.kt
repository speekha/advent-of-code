package com.adventofcode.dec2017.day12

import java.io.File

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day12/input.txt").readLines()
    with(NetworkManager()) {
        parse(input)
        println("Group size: ${browseGroup(0).size}")
        println("Count groups: ${countGroups()}")
    }
}