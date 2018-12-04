package com.adventofcode.dec2015.day06

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LightControllerTest {

    @Test
    fun `should start with empty grid`() {
        val controller = binaryLights()
        assertEquals(0, controller.measureLuminosity())
    }

    @Test
    fun `should turn all lights on`() {
        val input = listOf("turn on 0,0 through 999,999")
        val controller = binaryLights()
        controller.execute(input)
        assertEquals(1_000_000, controller.measureLuminosity())
    }

    @Test
    fun `should turn some lights on`() {
        val input = listOf("turn on 0,0 through 9,9")
        val controller = binaryLights()
        controller.execute(input)
        assertEquals(100, controller.measureLuminosity())
    }

    @Test
    fun `should turn off lights`() {
        val input = listOf("turn on 0,0 through 999,999", "turn off 0,0 through 9,9")
        val controller = binaryLights()
        controller.execute(input)
        assertEquals(999_900, controller.measureLuminosity())
    }

    @Test
    fun `should toggle lights`() {
        val input = listOf("turn on 0,0 through 999,999", "turn off 0,0 through 9,9", "toggle 8,8 through 12,12")
        val controller = binaryLights()
        controller.execute(input)
        assertEquals(999_883, controller.measureLuminosity())
    }

    @Test
    fun `should process actual data`() {
        val controller = binaryLights()
        controller.execute(readInputAsList())
        assertEquals(377891, controller.measureLuminosity())
    }

    @Test
    fun `should process actual gradual data`() {
        val controller = gradualLights()
        controller.execute(readInputAsList())
        assertEquals(14110788, controller.measureLuminosity())
    }

}