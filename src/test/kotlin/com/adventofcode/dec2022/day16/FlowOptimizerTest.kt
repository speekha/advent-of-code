package com.adventofcode.dec2022.day16

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FlowOptimizerTest {


    private val input = listOf(
        "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
        "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
        "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
        "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
        "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
        "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
        "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
        "Valve HH has flow rate=22; tunnel leads to valve GG",
        "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
        "Valve JJ has flow rate=21; tunnel leads to valve II"
    )

    @Test
    fun `should parse input`() {
        assertEquals(Valve("AA", flow = 0, next = listOf("DD", "II", "BB")), Valve(input[0]))
    }

    @Test
    fun `should release most pressure for 1 minute`() {
        val valves = input.map { Valve(it) }
        assertEquals(0, FlowOptimizer(valves).optimize( 1))
    }

    @Test
    fun `should release most pressure for 2 minutes`() {
        val valves = input.map { Valve(it) }
        assertEquals(0, FlowOptimizer(valves).optimize( 2))
    }

    @Test

    fun `should release most pressure for 3 minutes`() {
        val valves = input.map { Valve(it) }
        assertEquals(20, FlowOptimizer(valves).optimize( 3))
    }

    @Test

    fun `should release most pressure for 4 minutes`() {
        val valves = input.map { Valve(it) }
        assertEquals(40, FlowOptimizer(valves).optimize( 4))
    }

    @Test
    fun `should release most pressure for 5 minutes`() {
        val valves = input.map { Valve(it) }
        assertEquals(63, FlowOptimizer(valves).optimize( 5))
    }

    @Test
    fun `should release most pressure for 30 minutes`() {
        val valves = input.map { Valve(it) }
        assertEquals(1651, FlowOptimizer(valves).optimize( 30))
    }

    @Test
    fun `should release most pressure from actual valves for 30 minutes`() {
        val valves = actualInputList.map { Valve(it) }
        val result = FlowOptimizer(valves).optimize( 30)
        assertEquals(1820, result)
    }
}