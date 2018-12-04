package com.adventofcode.dec2020.day13

import com.adventofcode.lcm

class BusSchedule(input: String) {

    private val buses = input.split(",")
        .withIndex()
        .filter { (_, value) -> value != "x" }
        .map { (index, value) -> Bus(index, value.toInt()) }

    fun findNextBus(init: Int): Pair<Int, Int> {
        val departure = generateSequence(init) { it + 1 }
            .first { time ->
                println("testing $time")
                buses.any { time % it.period == 0 }
            }
        val wait = departure - init
        val bus = buses.first { departure % it.period == 0 }.period
        return bus to wait
    }

    fun findFirstSequence(): Long {
        val first = buses[0].period.toLong()
        val tests = buses.drop(1)
            .sortedByDescending { it.period }
        return tests.findSequenceStart(first)
    }

    private fun List<Bus>.findSequenceStart(first: Long) = fold(0L to first) { previous, current ->
        var (time, period) = previous
        while ((time + current.id) % current.period != 0L) {
            time += period
        }
        period = lcm(period, current.period.toLong())
        time to period
    }.first

    data class Bus(
        val id: Int,
        val period: Int
    )
}