package com.adventofcode.dec2020.day13

import com.adventofcode.lcm
import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BusScheduleTest {


    val input = "7,13,x,x,59,x,31,19"

    @Test
    fun `should find first bus`() {
        val schedule = BusSchedule(input)
        val (id, wait) = schedule.findNextBus(939)
        assertEquals(59, id)
        assertEquals(5, wait)
        assertEquals(295, id * wait)
    }

    @Test
    fun `should find first bus in actual data`() {
        val (departure, input) = readInputAsList()
        val schedule = BusSchedule(input)
        val (id, wait) = schedule.findNextBus(departure.toInt())
        println("Id: $id, wait: $wait")
        assertEquals(296, id * wait)
    }

    @Test
    fun `should find lcm`() {
        assertEquals(1, lcm(1))
        assertEquals(2, lcm(1, 2))
        assertEquals(6, lcm(1, 2, 3))
        assertEquals(30, lcm(5, 10, 15))
    }

    @Test
    fun `should find first sequence`() {
        val schedule = BusSchedule(input)
        assertEquals(1068781L, schedule.findFirstSequence())
    }

    @Test
    fun `should find first sequence in actual data`() {
        val schedule = BusSchedule(readInputAsList()[1])
        assertEquals(535296695251210, schedule.findFirstSequence())
    }
}