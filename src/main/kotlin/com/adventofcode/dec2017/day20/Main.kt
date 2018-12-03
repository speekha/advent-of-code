package com.adventofcode.dec2017.day20

import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day20/input.txt").readLines()
    with(ParticleSimulator(input)) {
        println("Particle #${findParticleWithBiggestAcceleration()}")
        for(i in 1..1000) {
            tick()
            println("Number of particles: ${particles.size}")
        }
    }
}