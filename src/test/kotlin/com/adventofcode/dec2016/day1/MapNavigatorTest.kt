package com.adventofcode.dec2016.day1

import org.junit.Assert.assertEquals
import org.junit.Test

class MapNavigatorTest {

    @Test
    fun direction_list_should_have_four_elements() {
        assertEquals(4, Direction.values().size)
    }

    @Test
    fun should_turn_right() {
        assertEquals(Direction.NORTH, Direction.WEST.turnRight())
        assertEquals(Direction.EAST, Direction.NORTH.turnRight())
        assertEquals(Direction.SOUTH, Direction.EAST.turnRight())
        assertEquals(Direction.WEST, Direction.SOUTH.turnRight())
    }

    @Test
    fun should_turn_left() {
        assertEquals(Direction.NORTH, Direction.EAST.turnLeft())
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft())
        assertEquals(Direction.SOUTH, Direction.WEST.turnLeft())
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft())
    }

    @Test
    fun north_of_position_should_be_up() {
        assertEquals(Position(0, 1), Position(0, 0).go(Direction.NORTH, 1))
    }

    @Test
    fun west_of_position_should_be_left() {
        assertEquals(Position(-1, 0), Position(0, 0).go(Direction.WEST, 1))
    }

    @Test
    fun south_of_position_should_be_down() {
        assertEquals(Position(0, -1), Position(0, 0).go(Direction.SOUTH, 1))
    }

    @Test
    fun east_of_position_should_be_right() {
        assertEquals(Position(1, 0), Position(0, 0).go(Direction.EAST, 1))
    }

    @Test
    fun check_move_5_steps() {
        assertEquals(Position(0, 5), Position(0, 0).go(Direction.NORTH, 5))
    }

    @Test
    fun result_should_be_5() {
        val input = "R2, L3"
        assertEquals(5, MapNavigator().calculateJourneyDestinationDistance(input))
    }

    @Test
    fun result_should_be_2() {
        val input = "R2, R2, R2"
        assertEquals(2, MapNavigator().calculateJourneyDestinationDistance(input))
    }

    @Test
    fun result_should_be_12() {
        val input = "R5, L5, R5, R3"
        assertEquals(12, MapNavigator().calculateJourneyDestinationDistance(input))
    }

    @Test
    fun hq_should_be_4() {
        val input = "R8, R4, R4, R8"
        assertEquals(4, MapNavigator().calculateHQDistance(input))
    }

    @Test
    fun taxicab_distance_should_be_5() {
        assertEquals(5, MapNavigator().calculateTaxicabDistance(Position(1, 1), Position(3, 4)))
    }
}

