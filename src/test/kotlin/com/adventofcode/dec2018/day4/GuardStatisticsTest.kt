package com.adventofcode.dec2018.day4

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GuardStatisticsTest {

    val input = listOf(
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-05 00:55] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-01 00:00] Guard #10 begins shift"
    )

    @Test
    fun `should parse data`() {
        val stats = GuardStatistics(input)
        assertEquals(17, stats.records.size)
        assertEquals(GuardRecord("[1518-11-01 00:00]", "#10", Event.SHIFT), stats.records[0])
    }

    @Test
    fun `should find most sleepy guard`() {
        val stats = GuardStatistics(input)
        assertEquals("#10", stats.findGuardWithMostSleep())
    }

    @Test
    fun `should find most sleepy minute`() {
        val stats = GuardStatistics(input)
        assertEquals(24, stats.findMostSleepedMinute("#10"))
    }

    @Test
    fun `should compute best combination`() {
        val stats = GuardStatistics(input)
        assertEquals(240, stats.findBestCombination())
    }

    @Test
    fun `should compute actual best combination`() {
        val input = readInputAsList()
        val stats = GuardStatistics(input)
        assertEquals(74743, stats.findBestCombination())
    }

    @Test
    fun `should find guard with most sleeped minute`() {
        val stats = GuardStatistics(input)
        assertEquals(4455, stats.findBestGuardMinuteCombination())
    }

    @Test
    fun `should find actual guard with most sleeped minute`() {
        val input = readInputAsList()
        val stats = GuardStatistics(input)
        assertEquals(132484, stats.findBestGuardMinuteCombination())
    }
}