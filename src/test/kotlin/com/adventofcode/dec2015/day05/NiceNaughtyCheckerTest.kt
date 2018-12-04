package com.adventofcode.dec2015.day05

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NiceNaughtyCheckerTest {

    @Test
    fun `should check nice or naughty`() {
        val checker = NiceNaughtyChecker()
        assertTrue(checker.isNice2014("ugknbfddgicrmopn"))
        assertFalse(checker.isNice2014("jchzalrnumimnmhp"))
        assertFalse(checker.isNice2014("haegwjzuvuyypxyu"))
        assertFalse(checker.isNice2014("dvszwmarrgswjxmb"))
    }

    @Test
    fun `should count nice names`() {
        val checker = NiceNaughtyChecker()
        val count = readInputAsList().count { checker.isNice2014(it) }
        assertEquals(258, count)
    }

    @Test
    fun `should check nice or naughty with new rules`() {
        val checker = NiceNaughtyChecker()
        assertTrue(checker.isNice2015("qjhvhtzxzqqjkmpb"))
        assertTrue(checker.isNice2015("xxyxx"))
        assertFalse(checker.isNice2015("uurcxstgmygtbstg"))
        assertFalse(checker.isNice2015("ieodomkazucvgmuy"))
        assertFalse(checker.isNice2015("aaaya"))
        assertTrue(checker.isNice2015("aaaaya"))
    }

    @Test
    fun `should count nice names with new rules`() {
        val checker = NiceNaughtyChecker()
        val count = readInputAsList().count { checker.isNice2015(it) }
        assertEquals(53, count)
    }
}