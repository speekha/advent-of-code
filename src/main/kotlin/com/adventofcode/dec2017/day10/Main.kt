package com.adventofcode.dec2017.day10

fun main(args: Array<String>) {
    val input = "147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70"
    with(KnotHasher(256, 64)) {
        val input1 = input.split(",")
                .map { it.toInt() }
                .toIntArray()
        println("Hash: ${simpleHash(input1)}")
    }
    with(KnotHasher(256, 64)) {
        println("Hash: ${digestToString(input)}")
    }
}