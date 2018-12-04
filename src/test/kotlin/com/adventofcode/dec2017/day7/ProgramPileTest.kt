package com.adventofcode.dec2017.day7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProgramPileTest {
    @Test
    fun `should parse input line without children`() {
        val input = "pbga (66)"
        assertEquals(ProgramPile.Node("pbga", 66, listOf()), ProgramPile().parseInputLine(input))
    }

    @Test
    fun `should parse input line with children`() {
        val input = "fwft (72) -> ktlj, cntj, xhth"
        assertEquals(ProgramPile.Node("fwft", 72, listOf("ktlj", "cntj", "xhth")), ProgramPile().parseInputLine(input))
    }

    val input = ("pbga (66)\n" +
            "xhth (57)\n" +
            "ebii (61)\n" +
            "havc (66)\n" +
            "ktlj (57)\n" +
            "fwft (72) -> ktlj, cntj, xhth\n" +
            "qoyq (66)\n" +
            "padx (45) -> pbga, havc, qoyq\n" +
            "tknk (41) -> ugml, padx, fwft\n" +
            "jptl (61)\n" +
            "ugml (68) -> gyxo, ebii, jptl\n" +
            "gyxo (61)\n" +
            "cntj (57)").split("\n")

    @Test
    fun `should find root`() {
        assertEquals("tknk", ProgramPile(input).findRoot().name)
    }

    @Test
    fun `should be 183`() {
        val parser = ProgramPile(input)
        assertEquals(183, parser.getChildrenWeight("ugml"))
    }

    @Test
    fun `should be 251`() {
        val parser = ProgramPile(input)
        assertEquals(251, parser.getTotalWeight("ugml"))
    }

    @Test
    fun `find unbalanced program`() {
        val parser = ProgramPile(input)
        assertEquals("ugml", parser.findUnbalancedProgram().name)
    }

    @Test
    fun `solve weight unbalance`() {
        val parser = ProgramPile(input)
        assertEquals(60, parser.correctWeight())
    }
}