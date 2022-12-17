package com.adventofcode.dec2022.day16

import java.util.LinkedList
import java.util.PriorityQueue

class FlowOptimizer {
    fun optimize(valves: Map<String, Valve>, minutes: Int): Int {
        val startConfiguration =
            FlowConfiguration(valves["AA"] ?: error("Missing starting point"), emptyList(), timeLeft = minutes)
        return optimize(startConfiguration, valves)
    }

    fun optimize(conf: FlowConfiguration, valves: Map<String, Valve>): Int = if (conf.timeLeft == 0) {
        println("Local max : " + conf.pressureReleased)
        conf.pressureReleased
    } else {
        val nextConfs = mutableListOf<FlowConfiguration>()
        if (conf.position.flow > 0 && conf.position !in conf.openedValves) {
            nextConfs += conf.open(valves)
        } else {

            conf.position.next
                .map { valves[it] ?: error("Incorrect valve: $it") }
                .filter { it !in conf.openedValves }
                .sortedByDescending { it.flow }
                .forEach { nextConfs.add(conf.moveTo(it)) }
        }
        if (nextConfs.isEmpty()) {
            conf.pressureReleased + conf.flowRate * conf.timeLeft
        } else {
            nextConfs.maxOf { optimize(it, valves) }
        }
    }

}
