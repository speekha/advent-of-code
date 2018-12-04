package com.adventofcode.dec2020.day12

import com.adventofcode.positioning.Position
import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ShipNavigationTest {

    val input = listOf(
        "F10",
        "N3",
        "F7",
        "R90",
        "F11"
    )

    @Test
    fun `should navigate according to instructions`() {
        val ship = ShipNavigation()
        ship.navigate(input)
        assertEquals(Position(17, 8), ship.position)
        assertEquals(25, ship.position.distance())
    }

    @Test
    fun `should navigate according to actual instructions`() {
        val ship = ShipNavigation()
        ship.navigate(readInputAsList())
        assertEquals(Position(-127, 752), ship.position)
        assertEquals(879, ship.position.distance())
    }

    @Test
    fun `should navigate with waypoint`() {
        val ship = ShipNavigation()
        ship.navigateWayPoint(input)
        assertEquals(Position(214, 72), ship.position)
        assertEquals(286, ship.position.distance())
    }

    @Test
    fun `should navigate with waypoint with actual instruction`() {
        val ship = ShipNavigation()
        ship.navigateWayPoint(readInputAsList())
        assertEquals(18107, ship.position.distance())
    }
}