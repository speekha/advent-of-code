package com.adventofcode.dec2017.day16

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class ProgramDanceTest {

    @Test
    fun `init program dance`() {
        val dance = ProgramDance(5)
        assertEquals("abcde", dance.positions())
    }

    private val input = listOf("s1", "x3/4", "pe/b")

    @Test
    fun `parse dance moves`() {
        val dance = ProgramDance(5)
        assertEquals(Spin(1), dance.parse(input[0]))
        assertEquals(Exchange(3, 4), dance.parse(input[1]))
        assertEquals(Swap('e', 'b'), dance.parse(input[2]))
    }

    @Test
    fun `process spin`() {
        val dance = ProgramDance(5)
        dance.move(dance.parse(input[0]))
        assertEquals("eabcd", dance.positions())
    }

    @Test
    fun `process exchange`() {
        val dance = ProgramDance(5)
        dance.move(dance.parse(input[1]))
        assertEquals("abced", dance.positions())
    }

    @Test
    fun `process swap`() {
        val dance = ProgramDance(5)
        dance.move(dance.parse(input[2]))
        assertEquals("aecdb", dance.positions())
    }

    @Test
    fun `process dance`() {
        val dance = ProgramDance(5)
        dance.doDance(input, 1)
        assertEquals("baedc", dance.positions())
    }

    @Test
    fun `double dance`() {
        val dance = ProgramDance(5)
        dance.doDance(input, 2)
        assertEquals("ceadb", dance.positions())
    }

    @Test
    fun `multiple dances`() {
        val output = listOf(
                "baedc",
                "ceadb",
                "ecbda",
                "abcde",
                "baedc",
                "ceadb",
                "ecbda",
                "abcde",
                "baedc",
                "ceadb")
        (0 until output.size).forEach {
            val dance = ProgramDance(5)
            dance.doDance(input, it + 1)
            assertEquals("$it", output[it], dance.positions())
        }
    }

    @Test
    fun `full set`() {
        val output = "kbednhopmfcjilag"
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day16/input.txt").readLines()[0].split(",")
        val dance = ProgramDance(16)
        dance.doDance(input)
        assertEquals(output, dance.positions())
    }

    @Test
    fun `multiple rounds`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day16/input.txt").readLines()[0].split(",")
        val output = listOf(
                "kbednhopmfcjilag",
                "jliebcdompnkhafg",
                "bfmjdoichnlkpage",
                "ldgkfohmnaibpejc",
                "lknafmjhgcpiedbo",
                "ebnikjlhomdagfcp",
                "aboicpgemdkfnlhj",
                "iedflnopmbgkhcaj",
                "blhgioajepmkdcfn",
                "gcjbfokmhnledipa")
        (0 until output.size).forEach {
            val dance = ProgramDance(16)
            dance.doDance(input, it + 1)
            assertEquals("$it", output[it], dance.positions())
        }
    }

    @Test
    fun `the million dances`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day16/input.txt").readLines()[0].split(",")
        val dance = ProgramDance(16)
        dance.doDance(input, 1000000000)
        assertEquals("fbmcgdnjakpioelh", dance.positions())
    }
}
