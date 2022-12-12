package com.adventofcode.dec2022.day10

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DisplayTest {

    val longInput = listOf(
        "addx 15",
        "addx -11",
        "addx 6",
        "addx -3",
        "addx 5",
        "addx -1",
        "addx -8",
        "addx 13",
        "addx 4",
        "noop",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx 5",
        "addx -1",
        "addx -35",
        "addx 1",
        "addx 24",
        "addx -19",
        "addx 1",
        "addx 16",
        "addx -11",
        "noop",
        "noop",
        "addx 21",
        "addx -15",
        "noop",
        "noop",
        "addx -3",
        "addx 9",
        "addx 1",
        "addx -3",
        "addx 8",
        "addx 1",
        "addx 5",
        "noop",
        "noop",
        "noop",
        "noop",
        "noop",
        "addx -36",
        "noop",
        "addx 1",
        "addx 7",
        "noop",
        "noop",
        "noop",
        "addx 2",
        "addx 6",
        "noop",
        "noop",
        "noop",
        "noop",
        "noop",
        "addx 1",
        "noop",
        "noop",
        "addx 7",
        "addx 1",
        "noop",
        "addx -13",
        "addx 13",
        "addx 7",
        "noop",
        "addx 1",
        "addx -33",
        "noop",
        "noop",
        "noop",
        "addx 2",
        "noop",
        "noop",
        "noop",
        "addx 8",
        "noop",
        "addx -1",
        "addx 2",
        "addx 1",
        "noop",
        "addx 17",
        "addx -9",
        "addx 1",
        "addx 1",
        "addx -3",
        "addx 11",
        "noop",
        "noop",
        "addx 1",
        "noop",
        "addx 1",
        "noop",
        "noop",
        "addx -13",
        "addx -19",
        "addx 1",
        "addx 3",
        "addx 26",
        "addx -30",
        "addx 12",
        "addx -1",
        "addx 3",
        "addx 1",
        "noop",
        "noop",
        "noop",
        "addx -9",
        "addx 18",
        "addx 1",
        "addx 2",
        "noop",
        "noop",
        "addx 9",
        "noop",
        "noop",
        "noop",
        "addx -1",
        "addx 2",
        "addx -37",
        "addx 1",
        "addx 3",
        "noop",
        "addx 15",
        "addx -21",
        "addx 22",
        "addx -6",
        "addx 1",
        "noop",
        "addx 2",
        "addx 1",
        "noop",
        "addx -10",
        "noop",
        "noop",
        "addx 20",
        "addx 1",
        "addx 2",
        "addx 2",
        "addx -6",
        "addx -11",
        "noop",
        "noop",
        "noop"
    )

    @Test
    fun `should run simple instructions`() {
        val input = listOf(
            "noop",
            "addx 3",
            "addx -5"
        )
        val display = Display(input)
        display.runInstructions(6)
        assertEquals(-1, display.x)
    }

    @Test
    fun `should compute signal strength`() {
        val display = Display(longInput)
        val sum = listOf(20, 40, 40, 40, 40, 40).sumOf {
            display.runInstructions(it)
            display.x * display.tick
        }
        assertEquals(13140, sum)
    }

    @Test
    fun `should compute actual signal strength`() {
        val display = Display(actualInputList)
        val sum = listOf(20, 40, 40, 40, 40, 40).sumOf {
            display.runInstructions(it)
            display.x * display.tick
        }
        assertTrue(sum > 14840)
        assertEquals(17180, sum)
    }

    @Test
    fun `should render screen`() {
        val display = Display(longInput)
        repeat(6) {y ->
            repeat(40) { x->
                display.runInstructions(1)
                print(if (x in display.x-1..display.x+1) "#" else ".")
            }
            println()
        }
    }
    @Test
    fun `should render actual screen`() {
        val display = Display(actualInputList)
        repeat(6) {y ->
            repeat(40) { x->
                display.runInstructions(1)
                print(if (x in display.x-1..display.x+1) "#" else " ")
            }
            println()
        }
    }
}