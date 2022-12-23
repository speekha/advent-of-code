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
        val start = FlowConfiguration(listOf(valves["AA"] ?: error("Missing starting point")), emptyList(), timeLeft = listOf(minutes))
        var max = 0
        val tested = mutableMapOf<String, FlowConfiguration>()
        val testable = valves.values.filter { it.flow > 0 }.asSequence()
        val processor = PrioritizedQueueProcessor(start)
        processor.process { conf ->
            val nextConf = testable
                .filter { it !in conf.openedValves }
                .map { it to distance(conf.positions[0].name, it.name) }
                .filter { (_, d) -> d < conf.timeLeft[0] }
                .map { (valve, distance) ->
                    val remaining = conf.timeLeft[0] - distance - 1
                    FlowConfiguration(
                        listOf(valve),
                        conf.openedValves + valve,
                        conf.pressureReleased + valve.flow * remaining,
                        listOf(remaining)
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

    fun optimize(participants: Int, minutes: Int): Int {
        val startValve = valves["AA"] ?: error("Missing starting point")
        val start = FlowConfiguration((1..participants).map { startValve }, emptyList(), timeLeft = (1..participants).map { minutes })
        var max = 0
        val tested = mutableMapOf<String, FlowConfiguration>()
        val testable = valves.values.filter { it.flow > 0 }.asSequence()
        val processor = PrioritizedQueueProcessor(start)
        processor.process { conf ->
            val nextConf = mutableListOf<FlowConfiguration>()
            testable
                .filter { it !in conf.openedValves }
                .map { findClosestToValve(it, conf)}
                .filter { (_,_, d) -> d > 0 }
                .map { (valve, i, remaining) ->
                    FlowConfiguration(
                        conf.positions.indices.map { if (it == i) valve else conf.positions[it] },
                        conf.openedValves + valve,
                        conf.pressureReleased + valve.flow * remaining,
                        conf.timeLeft.indices.map { if (it == i) remaining else conf.timeLeft[it] },
                    )
                }.forEach { nextConf.add(it) }
            if (nextConf.isEmpty()) {
                if (conf.pressureReleased > max) {
                    max = conf.pressureReleased
                    println("New max: $max (queue size : ${processor.queue.size})")
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

    fun findClosestToValve(valve: Valve, conf: FlowConfiguration) : Triple<Valve, Int, Int> {
        var closest = 0
        var maxRemaining = 0
        conf.positions.indices.forEach {i ->
            val dist = conf.timeLeft[i] - distance(valve.name, conf.positions[i].name) - 1
            if (maxRemaining < dist) {
                maxRemaining = dist
                closest = i
            }
        }
        return Triple(valve, closest, maxRemaining)
    }
}
