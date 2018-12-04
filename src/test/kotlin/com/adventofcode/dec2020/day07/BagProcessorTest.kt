package com.adventofcode.dec2020.day07

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BagProcessorTest {

    val input = listOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags."
    )

    @Test
    fun `should pare rule`() {
        val processor = BagProcessor(input)
        assertEquals(
            Rule("light red", listOf(1 to "bright white", 2 to "muted yellow")),
            processor.containerRules["light red"]
        )
        assertEquals(Rule("bright white", listOf(1 to "shiny gold")), processor.containerRules["bright white"])
        assertEquals(Rule("dotted black", emptyList()), processor.containerRules["dotted black"])
    }

    @Test
    fun `should find potential containers`() {
        val processor = BagProcessor(input)
        assertEquals(4, processor.findContainers("shiny gold").size)
    }

    @Test
    fun `should find potential containers in actual data`() {
        val processor = BagProcessor(readInputAsList())
        assertEquals(148, processor.findContainers("shiny gold").size)
    }

    @Test
    fun `should find potential content`() {
        val processor = BagProcessor(input)
        assertEquals(32, processor.findContent("shiny gold") - 1)
    }

    @Test
    fun `should find potential content in actual data`() {
        val processor = BagProcessor(readInputAsList())
        assertEquals(24867, processor.findContent("shiny gold") - 1)
    }
}