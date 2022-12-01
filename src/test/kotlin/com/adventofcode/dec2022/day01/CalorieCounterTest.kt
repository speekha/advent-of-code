package com.adventofcode.dec2022.day01

import com.adventofcode.actualInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalorieCounterTest {

    val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()

    @Test
    fun `should count calories per elf`() {
        val elves = CalorieCounter().parseCalories(input)
        assertEquals(listOf(6000, 4000, 11000, 24000, 10000), elves)
    }

    @Test
    fun `should find elf with the most calories`() {
        val elf = CalorieCounter().findElfWithMostCalories(input)
        assertEquals(24000, elf)
    }

    @Test
    fun `should find actual elf with the most calories`() {
        val elf = CalorieCounter().findElfWithMostCalories(actualInput)
        assertEquals(69177, elf)
    }

    @Test
    fun `should find elves with the most calories`() {
        val elf = CalorieCounter().findElvesWithMostCalories(input)
        assertEquals(45000, elf)
    }

    @Test
    fun `should find actual elves with the most calories`() {
        val elf = CalorieCounter().findElvesWithMostCalories(actualInput)
        assertEquals(207456, elf)
    }
}