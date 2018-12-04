package com.adventofcode.dec2017.day22

import java.io.File

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day22/input.txt").readLines()
    with(Virus(input)) {
        for(i in 1..10000) {
            simpleInfectionBurst()
        }
        println("Infection count: ${infections}")
    }
    with(Virus(input)) {
        for(i in 1..10000000) {
            evolvedInfectionBurst()
        }
        println("Infection count: ${infections}")
    }
}