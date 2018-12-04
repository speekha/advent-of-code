package com.adventofcode.dec2017.day22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun `should turn left`() {
        Assertions.assertEquals(Direction.LEFT, Direction.UP.turnLeft())
        Assertions.assertEquals(Direction.UP, Direction.RIGHT.turnLeft())
        Assertions.assertEquals(Direction.RIGHT, Direction.DOWN.turnLeft())
        Assertions.assertEquals(Direction.DOWN, Direction.LEFT.turnLeft())
    }

    @Test
    fun `should turn right`() {
        Assertions.assertEquals(Direction.RIGHT, Direction.UP.turnRight())
        Assertions.assertEquals(Direction.DOWN, Direction.RIGHT.turnRight())
        Assertions.assertEquals(Direction.LEFT, Direction.DOWN.turnRight())
        Assertions.assertEquals(Direction.UP, Direction.LEFT.turnRight())
    }

    @Test
    fun `should reverse`() {
        Assertions.assertEquals(Direction.RIGHT, Direction.LEFT.reverse())
        Assertions.assertEquals(Direction.DOWN, Direction.UP.reverse())
        Assertions.assertEquals(Direction.LEFT, Direction.RIGHT.reverse())
        Assertions.assertEquals(Direction.UP, Direction.DOWN.reverse())
    }
}