package com.adventofcode.dec2017.day22

import org.junit.Assert
import org.junit.Test

class DirectionTest {

    @Test
    fun `should turn left`() {
        Assert.assertEquals(Direction.LEFT, Direction.UP.turnLeft())
        Assert.assertEquals(Direction.UP, Direction.RIGHT.turnLeft())
        Assert.assertEquals(Direction.RIGHT, Direction.DOWN.turnLeft())
        Assert.assertEquals(Direction.DOWN, Direction.LEFT.turnLeft())
    }

    @Test
    fun `should turn right`() {
        Assert.assertEquals(Direction.RIGHT, Direction.UP.turnRight())
        Assert.assertEquals(Direction.DOWN, Direction.RIGHT.turnRight())
        Assert.assertEquals(Direction.LEFT, Direction.DOWN.turnRight())
        Assert.assertEquals(Direction.UP, Direction.LEFT.turnRight())
    }

    @Test
    fun `should reverse`() {
        Assert.assertEquals(Direction.RIGHT, Direction.LEFT.reverse())
        Assert.assertEquals(Direction.DOWN, Direction.UP.reverse())
        Assert.assertEquals(Direction.LEFT, Direction.RIGHT.reverse())
        Assert.assertEquals(Direction.UP, Direction.DOWN.reverse())
    }
}