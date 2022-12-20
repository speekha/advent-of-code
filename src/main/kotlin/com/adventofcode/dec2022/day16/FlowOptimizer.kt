package com.adventofcode.dec2022.day16

import com.adventofcode.PrioritizedQueueProcessor
import kotlin.math.min

class FlowOptimizer(
    input: List<Valve>
) {

    private val valves: Map<String, Valve> = input.associateBy { it.name }

    private val distances = mutableMapOf<String, MutableMap<String, Int>>()

    init {
        initDistances()
    }

    private fun initDistances() {
        val iteration: MutableMap<String, List<String>> =
            valves.mapValues { (_, value) -> listOf(value.name) }.toMutableMap()
        for (distance in 0..valves.size) {
            iteration.entries.forEach { (k, v) ->
                setDistances(k, v, distance)
                iteration[k] =
                    v.flatMap { valve -> valves[valve]!!.next.filter { it !in (distances[k]?.keys ?: setOf()) } }
            }
        }
    }

    private fun setDistances(a: String, b: List<String>, distance: Int) {
        val destinations = distances.getOrPut(a) { mutableMapOf() }
        b.forEach {
            destinations[it] = min(destinations.getOrDefault(it, Integer.MAX_VALUE), distance)
        }
    }

    fun distance(a: String, b: String) = distances[a]?.get(b) ?: Integer.MAX_VALUE

    fun optimize(minutes: Int): Int {
        val start = FlowConfiguration(valves["AA"] ?: error("Missing starting point"), emptyList(), timeLeft = minutes)
        var max = 0
        val tested = mutableMapOf<String, FlowConfiguration>()
        val testable = valves.values.filter { it.flow > 0 }.asSequence()
        val processor = PrioritizedQueueProcessor(start)
        processor.process { conf ->
            val nextConf = testable
                .filter { it !in conf.openedValves }
                .map { it to distance(conf.position.name, it.name) }
                .filter { (_, d) -> d < conf.timeLeft }
                .map { (valve, distance) ->
                    val remaining = conf.timeLeft - distance - 1
                    FlowConfiguration(
                        valve,
                        conf.openedValves + valve,
                        conf.pressureReleased + valve.flow * remaining,
                        remaining
                    )
                }.toMutableList()
            if (nextConf.isEmpty()) {
                if (conf.pressureReleased > max) {
                    max = conf.pressureReleased
                }
            } else {
                val alreadyTested = nextConf.filter { it.key() in tested }
                alreadyTested.forEach {
                    if (it.pressureReleased > tested[it.key()]!!.pressureReleased) {
                        processor.prune(tested[it.key()]!!)
                    } else {
                        nextConf.remove(it)
                    }
                }
            }
            nextConf.sortedByDescending { it.pressureReleased }
        }
        return max
    }

    fun optimizeWithElephant(minutes: Int): Int {
        val start = FlowConfiguration(valves["AA"] ?: error("Missing starting point"), emptyList(), timeLeft = minutes)
        var max = 0
        val tested = mutableMapOf<String, FlowConfiguration>()
        val testable = valves.values.filter { it.flow > 0 }.asSequence()
        val processor = PrioritizedQueueProcessor(start)
        processor.process { conf ->
            val nextConf = testable
                .filter { it !in conf.openedValves }
                .map { it to distance(conf.position.name, it.name) }
                .filter { (_, d) -> d < conf.timeLeft }
                .map { (valve, distance) ->
                    val remaining = conf.timeLeft - distance - 1
                    FlowConfiguration(
                        valve,
                        conf.openedValves + valve,
                        conf.pressureReleased + valve.flow * remaining,
                        remaining
                    )
                }.toMutableList()
            if (nextConf.isEmpty()) {
                if (conf.pressureReleased > max) {
                    max = conf.pressureReleased
                }
            } else {
                val alreadyTested = nextConf.filter { it.key() in tested }
                alreadyTested.forEach {
                    if (it.pressureReleased > tested[it.key()]!!.pressureReleased) {
                        processor.prune(tested[it.key()]!!)
                    } else {
                        nextConf.remove(it)
                    }
                }
            }
            nextConf.sortedByDescending { it.pressureReleased }
        }
        return max
    }
}
