package com.adventofcode.dec2017.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class VirusTester {

    val input = listOf("..#",
            "#..",
            "...")

    val output = listOf(
            listOf(
                    ".........",
                    ".........",
                    ".........",
                    ".....#...",
                    "...#.....",
                    ".........",
                    ".........",
                    ".........",
                    "........."),
            listOf(
                    ".........",
                    ".........",
                    ".........",
                    ".....#...",
                    "...##....",
                    ".........",
                    ".........",
                    ".........",
                    "........."),
            listOf(
                    ".........",
                    ".........",
                    ".........",
                    ".....#...",
                    "....#....",
                    ".........",
                    ".........",
                    ".........",
                    ".........")
    )

    val evolvedOutput = listOf(
            listOf(
                    ".........",
                    ".........",
                    ".........",
                    ".....#...",
                    "...#W....",
                    ".........",
                    ".........",
                    ".........",
                    "........."
            ),
            listOf(
                    ".........",
                    ".........",
                    ".........",
                    ".....#...",
                    "...FW....",
                    ".........",
                    ".........",
                    ".........",
                    "........."
            )
    )

    @Test
    fun `should parse initial input`() {
        val virus = Virus(input)
        assertEquals(input, virus.viewMap(3))
        assertEquals(output[0], virus.viewMap(9))
        assertEquals(Position(0, 0), virus.position)
        assertEquals(Direction.UP, virus.direction)
    }

    @Test
    fun `should process steps`() {
        val virus = Virus(input)
        (1..output.indices.last).forEach {
            virus.simpleInfectionBurst()
            assertEquals(output[it], virus.viewMap(9), "Error at step $it")
        }
    }

    @Test
    fun `should count infections`() {
        val virus = Virus(input)
        (1..7).forEach {
            virus.simpleInfectionBurst()
        }
        assertEquals(5, virus.infections)
    }

    @Test
    fun `should process 70 steps`() {
        val virus = Virus(input)
        val output = listOf(
                ".....##..",
                "....#..#.",
                "...#....#",
                "..#.#...#",
                "..#.#..#.",
                ".....##..",
                ".........",
                ".........",
                ".........")
        (1..70).forEach {
            virus.simpleInfectionBurst()
        }
        assertEquals(output, virus.viewMap(9))
        assertEquals(41, virus.infections)
    }

    @Test
    fun `should process 10000 steps`() {
        val virus = Virus(input)
        (1..10000).forEach {
            virus.simpleInfectionBurst()
        }
        assertEquals(5587, virus.infections)
    }

    @Test
    fun `test real simple infection`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day22/input.txt").readLines()
        with(Virus(input)) {
            for (i in 1..10000) {
                simpleInfectionBurst()
            }
            assertEquals(5305, infections)
        }
    }

    @Test
    fun `should process 6 first steps of evolved infection`() {
        val virus = Virus(input)
        evolvedOutput.forEach {
            virus.evolvedInfectionBurst()
            assertEquals(it, virus.viewMap(9))
        }
    }

    @Test
    fun `should process steps of evolved infection`() {
        val virus = Virus(input)
        val output = listOf(
                ".........",
                ".........",
                ".........",
                "..WW.#...",
                "..#.W....",
                ".........",
                ".........",
                ".........",
                ".........")
        (1..7).forEach {
            virus.evolvedInfectionBurst()
        }
        assertEquals(output, virus.viewMap(9))
    }

    @Test
    fun `should process 100 steps`() {
        val virus = Virus(input)
        (1..100).forEach {
            virus.evolvedInfectionBurst()
        }
        assertEquals(26, virus.infections)
    }

    @Test
    fun `should process 10000000 steps`() {
        val virus = Virus(input)
        (1..10000000).forEach {
            virus.evolvedInfectionBurst()
        }
        assertEquals(2511944, virus.infections)
    }


    @Test
    fun `test real evolved infection`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day22/input.txt").readLines()
        with(Virus(input)) {
            for (i in 1..10000000) {
                evolvedInfectionBurst()
            }
            assertEquals(2511424, infections)
        }
    }
}