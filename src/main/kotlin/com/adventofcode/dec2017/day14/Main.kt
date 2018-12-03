package com.adventofcode.dec2017.day14

fun main(args: Array<String>) {
    val input = "amgozmfv"
    with(DefragMonitor(input)) {
        println("Used squares: ${countUsedSquares()}")
        println("Used regions: ${countRegions()}")
    }
}