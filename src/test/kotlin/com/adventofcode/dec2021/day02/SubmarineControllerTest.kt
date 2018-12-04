package com.adventofcode.dec2021.day02

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SubmarineControllerTest {

    private val controller = SubmarineController()

    private val input = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    @Test
    fun `should compute final position`() {
        assertEquals(SubmarineState(15, 60, 10), controller.navigate(input))
    }

    @Test
    fun `should compute actual final position`() {
        val position = controller.navigate(actualInputList)
        assertEquals(1893605, position.position * position.aim)
        assertEquals(2120734350, position.position * position.depth)
    }
}