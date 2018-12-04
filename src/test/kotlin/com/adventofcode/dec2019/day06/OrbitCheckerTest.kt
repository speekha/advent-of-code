package com.adventofcode.dec2019.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class OrbitCheckerTest {
    @Test
    fun `should parse input`() {
        val checker = OrbitChecker(
                """COM)B
                |B)C
                |C)D
                |D)E
                |E)F
                |B)G
                |G)H
                |D)I
                |E)J
                |J)K
                |K)L""".trimMargin().split("\n")
        )
        assertEquals("COM", checker.orbitalTreeRoot.name)
        assertEquals(1, checker.orbitalTreeRoot.children.size)
        assertEquals("B", checker.orbitalTreeRoot.children[0].name)
        assertEquals(2, checker.orbitalTreeRoot.children[0].children.size)
    }

    @Test
    fun `should count orbits`() {
        val checker = OrbitChecker(
                """COM)B
                |B)C
                |C)D
                |D)E
                |E)F
                |B)G
                |G)H
                |D)I
                |E)J
                |J)K
                |K)L""".trimMargin().split("\n")
        )
        assertEquals(42, checker.countOrbits())
    }

    @Test
    fun `should count actual orbits`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day06/input.txt").readLines()
        val checker = OrbitChecker(input)
        assertEquals(122782, checker.countOrbits())
    }


    @Test
    fun `should count orbital transfers`() {
        val checker = OrbitChecker(
                """COM)B
                |B)C
                |C)D
                |D)E
                |E)F
                |B)G
                |G)H
                |D)I
                |E)J
                |J)K
                |K)L
                |K)YOU
                |I)SAN""".trimMargin().split("\n")
        )
        assertEquals(4, checker.countOrbitalTransfers("YOU", "SAN"))
    }

    @Test
    fun `should count actual orbital transfers`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day06/input.txt").readLines()
        val checker = OrbitChecker(input)
        assertEquals(271, checker.countOrbitalTransfers("YOU", "SAN"))
    }

}