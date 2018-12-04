package com.adventofcode.dec2019.day07

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

class ThrustOptimizer(
        val input: String
) {

    fun maximizeOutput(feedback: Boolean): Long {
        val phases = if (feedback) {
            listOf(5, 6, 7, 8, 9)
        } else {
            listOf(0, 1, 2, 3, 4)
        }
        return optimize(listOf(), phases.map { it.toLong() })
    }

    private fun optimize(phases: List<Long>, remainder: List<Long>): Long = if (remainder.size == 1) {
        computeOutput((phases + remainder[0]).toLongArray())
    } else {
        remainder.map {
            optimize(phases + it, remainder - it)
        }.maxOrNull() ?: error("Max not found")
    }

    fun computeOutput(phases: LongArray): Long = runBlocking {
        val channels = Array(5) { Channel<Long>(2) }
        val amps = Array(5) {
            Amplifier(input, channels[it], channels[(it + 1) % channels.size]).apply {
                initPhase(phases[it])
            }
        }
        channels[0].send(0L)
        val jobs = amps.map { it.process() }
        jobs.forEach { it.join() }
        channels.first().receive()
    }

}