package com.adventofcode.dec2022.day16

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FlowOptimizerTest {


    private val input = listOf("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
        "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
        "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
        "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
        "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
        "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
        "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
        "Valve HH has flow rate=22; tunnel leads to valve GG",
        "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
        "Valve JJ has flow rate=21; tunnel leads to valve II")

    @Test
    fun `should parse input`() {
        assertEquals(Valve("AA", flow = 0, next = listOf("DD", "II", "BB")), Valve(input[0]))
    }

    @Test
    fun `should count pressure for one opened valve`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        var flow = FlowConfiguration(valves["BB"]!!, timeLeft = 30)
        flow = flow.open(valves)
        assertEquals( FlowConfiguration(valves["BB"]!!, listOf(valves["BB"]?:error("Unknown valve")), 13, 0, 29), flow)
    }

    @Test
    fun `should count pressure when moving to next valve`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        var flow = FlowConfiguration(valves["BB"]!!, timeLeft = 30)
        flow = flow.open(valves)
        flow = flow.moveTo(valves["CC"]!!)
        assertEquals( FlowConfiguration(valves["CC"]!!, listOf(valves["BB"]?:error("Unknown valve")), 13, 13, 28), flow)
    }

    @Test
    fun `should release most pressure for 1 minute`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        assertEquals(0, FlowOptimizer().optimize(valves, 1))
    }

    @Test
    fun `should release most pressure for 2 minutes`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        assertEquals(0, FlowOptimizer().optimize(valves, 2))
    }

    @Test

    fun `should release most pressure for 3 minutes`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        assertEquals(20, FlowOptimizer().optimize(valves, 3))
    }

    @Test

    fun `should release most pressure for 4 minutes`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        assertEquals(40, FlowOptimizer().optimize(valves, 4))
    }

    @Test
    fun `should release most pressure for 5 minutes`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        assertEquals(63, FlowOptimizer().optimize(valves, 5))
    }

    @Test
    fun `should release most pressure for 30 minutes`() {
        val valves = input.map { Valve(it) }.associateBy { it.name }
        assertEquals(1651, FlowOptimizer().optimize(valves, 30))
    }
}