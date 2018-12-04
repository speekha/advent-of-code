package com.adventofcode.dec2020.day19

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RuleMatcherTest {

    val input = listOf(
        "0: 4 1 5",
        "1: 2 3 | 3 2",
        "2: 4 4 | 5 5",
        "3: 4 5 | 5 4",
        "4: \"a\"",
        "5: \"b\"",
        "",
        "ababbb",
        "bababa",
        "abbbab",
        "aaabbb",
        "aaaabbb"
    )

    @Test
    fun `should parse rules`() {
        val matcher = RuleMatcher(input)
        assertEquals(6, matcher.rules.size)
        assertEquals(5, matcher.messages.size)
        assertEquals(Token("a"), matcher.rules["4"])
        assertEquals(Token("b"), matcher.rules["5"])
        assertEquals(SequenceRule(listOf("4", "1", "5")), matcher.rules["0"])
        assertEquals(
            CompoundRule(listOf(SequenceRule(listOf("2", "3")), SequenceRule(listOf("3", "2")))),
            matcher.rules["1"]
        )
    }

    @Test
    fun `should validate messages`() {
        val matcher = RuleMatcher(input)
        assertTrue(matcher.checkMessageValidity(matcher.messages[0]))
        assertTrue(matcher.checkMessageValidity(matcher.messages[0]))
        assertTrue(matcher.checkMessageValidity(matcher.messages[2]))
        assertFalse(matcher.checkMessageValidity(matcher.messages[1]))
    }

    @Test
    fun `should count valid messages`() {
        val matcher = RuleMatcher(input)
        assertEquals(2, matcher.countValidMessages())
    }

    @Test
    fun `should count actual valid messages`() {
        val matcher = RuleMatcher(readInputAsList())
        assertEquals(220, matcher.countValidMessages())
    }

    @Test
    fun `should count actual valid messages with recursive rules`() {
        val matcher = RuleMatcher(readInputAsList())
        assertEquals(439, matcher.countAlternateValidMessages())
    }
}