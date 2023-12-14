package com.adventofcode.dec2023.day14

import com.adventofcode.actualInputList
import com.adventofcode.positioning.CardinalDirection
import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Orientation
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ParabolicBalancerTest {

    val input = listOf(
        "O....#....",
        "O.OO#....#",
        ".....##...",
        "OO.#O....O",
        ".O.....O#.",
        "O.#..O.#.#",
        "..O..#O..O",
        ".......O..",
        "#....###..",
        "#OO..#...."
    )

    @Test
    fun `tilt platform and compute weigh`() {
        val balancer = ParabolicBalancer(input)
        balancer.tiltNorth()
        assertEquals(136, balancer.weigh())
    }

    @Test
    fun `tilt platform and compute actual weight`() {
        val balancer = ParabolicBalancer(actualInputList)
        balancer.tiltNorth()
        assertEquals(106517, balancer.weigh())
    }

    @Test
    fun `cycle platform and compute weigh`() {
        val balancer = ParabolicBalancer(input)
        balancer.cycle()
        assertEquals(87, balancer.weigh())
    }

    @Test
    fun `cycle platform several times and compute weigh`() {
        val balancer = ParabolicBalancer(input)
        balancer.cycleAndWeigh(1000000000)
        assertEquals(64, balancer.weigh())
    }

    @Test
    fun `cycle platform several times and compute actual weigh`() {
        val balancer = ParabolicBalancer(actualInputList)
        balancer.cycleAndWeigh(1000000000)
        assertEquals(79723, balancer.weigh())
    }
}