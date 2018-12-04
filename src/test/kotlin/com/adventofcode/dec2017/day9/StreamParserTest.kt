package com.adventofcode.dec2017.day9

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StreamParserTest {

    @Test
    fun `should remove ignored characters`() {
        val input = "a!a!<!>{!{!}}a"
        assertEquals("Failed to clean $input", "a{}a", StreamParser().removeIgnored(input))
    }

    @Test
    fun `should remove garbage`() {
        val parser = StreamParser()
        assertEquals("a<>a", parser.removeGarbage("a<a>a"))
        assertEquals("a<>a", parser.removeGarbage("a<random characters>a"))
        assertEquals("a<>a", parser.removeGarbage("a<<<<>a"))
        assertEquals("a<>a", parser.removeGarbage("a<{!>}>a"))
        assertEquals("a<>a", parser.removeGarbage("a<!!>a"))
        assertEquals("a<>a", parser.removeGarbage("a<!!!>>a"))
        assertEquals("a<>a", parser.removeGarbage("a<{o\"i!a,<{i<a>a"))
        assertEquals("a<>a<>a", parser.removeGarbage("a<a>a<a>a"))
    }

    @Test
    fun `should have proper scores`() {
        val parser = StreamParser()
        assertEquals(1, parser.score("{}"))
        assertEquals(6, parser.score("{{{}}}"))
        assertEquals(5, parser.score("{{},{}}"))
        assertEquals(16, parser.score("{{{},{},{{}}}}"))
        assertEquals(1, parser.score("{<a>,<a>,<a>,<a>}"))
        assertEquals(9, parser.score("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
        assertEquals(9, parser.score("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
        assertEquals(3, parser.score("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
    }

    @Test
    fun `should count garbage characters`() {
        val parser = StreamParser()
        assertEquals(0, parser.garbageCount("<>"))
        assertEquals(17,parser.garbageCount( "<random characters>"))
        assertEquals(3, parser.garbageCount("<<<<>"))
        assertEquals(2, parser.garbageCount("<{!>}>"))
        assertEquals(0, parser.garbageCount("<!!>"))
        assertEquals(0, parser.garbageCount("<!!!>>"))
        assertEquals(10, parser.garbageCount("<{o\"i!a,<{i<a>"))
    }
}