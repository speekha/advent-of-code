package com.adventofcode.dec2017.day20

import kotlin.math.abs

class ParticleSimulator(input: List<String>) {

    var particles = input.map { Particle.parse(it) }

    fun findParticleWithBiggestAcceleration() = particles.indices.minByOrNull {
        abs(particles[it].x.acceleration) + abs(particles[it].y.acceleration) + abs(particles[it].z.acceleration)
    } ?: -1

    fun tick() {
        val nextState = particles.map { it.computeNextPosition() }
        particles = nextState.groupBy { Coordinates(it.x.position, it.y.position, it.z.position) }
                .filter { it.value.size == 1 }
                .asSequence()
                .map { it.value[0] }
                .toList()
    }
}