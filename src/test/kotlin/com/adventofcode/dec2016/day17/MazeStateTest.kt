package com.adventofcode.dec2016.day17

import com.adventofcode.dec2016.day17.Direction.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MazeStateTest {

    @Test
    fun `should find open doors`() {
        val pathFinder = MazeState(Position(0, 0), "hijkl")
        assertTrue(pathFinder.isDoorOpen(UP))
        assertTrue(pathFinder.isDoorOpen(LEFT))
        assertTrue(pathFinder.isDoorOpen(DOWN))
        assertFalse(pathFinder.isDoorOpen(RIGHT))
    }

    @Test
    fun `should find next positions`() {
        val pathFinder = MazeState(Position(0, 0), "hijkl")
        val next = pathFinder.nextPositions()
        assertEquals(listOf(MazeState(Position(1, 0), "hijklD", listOf(true, false, true, true))), next)
        assertEquals(listOf(
                MazeState(Position(0, 0), "hijklDU", listOf(false, false, false, true)),
                MazeState(Position(1, 1), "hijklDR", listOf(false, false, false, false))
        ), next[0].nextPositions())
    }

}