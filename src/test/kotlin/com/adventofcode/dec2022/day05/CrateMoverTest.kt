package com.adventofcode.dec2022.day05

import com.adventofcode.actualInput
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class CrateMoverTest {

    val input = listOf(
        "    [D]    ",
        "[N] [C]    ",
        "[Z] [M] [P]",
        " 1   2   3 ",
        "",
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2"
    )

    @Test
    fun `should parse input`() {
        val expected = CrateMover9000(
            arrayOf(
                pileOf('N', 'Z'),
                pileOf('D', 'C', 'M'),
                pileOf('P')
            )
        ).toString()
        assertEquals(expected, CrateMover9000(input.take(4)).toString())
    }

    @Test
    fun `should sort crates`() {
        val expected = CrateMover9000(
            arrayOf(
                pileOf('C'),
                pileOf('M'),
                pileOf('Z', 'N', 'D', 'P')
            )
        ).toString()
        val sorter = CrateMover9000(input.take(4))
        sorter.moveCrates(input.drop(5))
        assertEquals(expected, sorter.toString())
    }

    @Test
    fun `should sort crates and extract message`() {
        val sorter = CrateMover9000(input.take(4))
        sorter.moveCrates(input.drop(5))
        assertEquals("CMZ", sorter.getMessage())
    }

    @Test
    fun `should sort crates and extract actual message`() {
        val (initial, moves) = actualInput.split("\n\n")
        val sorter = CrateMover9000(initial.split("\n"))
        sorter.moveCrates(moves.split("\n"))
        assertEquals("BSDMQFLSP", sorter.getMessage())
    }


    @Test
    fun `should sort crates with 9001 and extract message`() {
        val sorter = CrateMover9001(input.take(4))
        sorter.moveCrates(input.drop(5))
        assertEquals("MCD", sorter.getMessage())
    }

    @Test
    fun `should sort crates with 9001 and extract actual message`() {
        val (initial, moves) = actualInput.split("\n\n")
        val sorter = CrateMover9001(initial.split("\n"))
        sorter.moveCrates(moves.split("\n"))
        assertEquals("PGSQBFLDP", sorter.getMessage())
    }

    private fun pileOf(vararg crates: Char) = LinkedList<Char>().apply {
        addAll(crates.toList())
    }
}