package com.adventofcode.dec2016.day15

import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day15/input.txt").readLines()
    var sculpture = parseFile(input)
    println("Drop capsule at: ${sculpture.calculateDropTime()}s")
    sculpture = sculpture.copy(sculpture.discs + Disc(11, 0))
    println("Drop capsule at: ${sculpture.calculateDropTime()}s")
}

fun parseFile(input: List<String>): DiscSculpture {
    return DiscSculpture(input.map {
        parseLine(it)
    })
}

fun parseLine(line: String): Disc {
    val slots = line.subSequence(line.indexOf("has ") + 4, line.indexOf("positions") - 1).toString()
    val position = line.subSequence(line.lastIndexOf(" ") + 1, line.length - 1).toString()
    return Disc(slots.toInt(), position.toInt())
}