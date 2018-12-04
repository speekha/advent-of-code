package com.adventofcode.dec2018.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FightSimulatorTest {

    @Test
    fun `should parse input`() {
        val input = listOf(
                "#######",
                "#.G.E.#",
                "#E.G.E#",
                "#.G.E.#",
                "#######")

        val simulator = FightSimulator(input)
        assertEquals(7, simulator.fighters.size)
        assertEquals(Fighter.Goblin(Position(2,1)), simulator.fighters[0])
        assertEquals(Fighter.Elf(Position(4,1)), simulator.fighters[1])
    }

    @Test
    fun `should identifie enemies`() {
        val input = listOf(
                "#######",
                "#.G.E.#",
                "#E.G.E#",
                "#.G.E.#",
                "#######")

        val simulator = FightSimulator(input)
        assertEquals(4, simulator.findTargets(0).size)
        assertEquals(3, simulator.findTargets(1).size)
    }

    @Test
    fun `should move towards target`() {
        val input = listOf(
                "#######",
                "#E..G.#",
                "#...#.#",
                "#.G.#G#",
                "#######")

        val simulator = FightSimulator(input)
        simulator.iterate()
        assertEquals(Fighter.Elf(Position(2,1)), simulator.fighters[0])
    }
}