package com.adventofcode.dec2016.day20

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class IPBlacklistManagerTest {

    val ipManager = IPRangeManager()

    @Test
    fun `should add a first range`() {
        val range = 0.toLong()..10.toLong()
        ipManager.addBlackListRanges(listOf(range))
        Assertions.assertEquals(listOf(range), ipManager.getBlackList())
    }

    @Test
    fun `should add non-overlapping ranges`() {
        val range1 = 0.toLong()..10.toLong()
        val range2 = 20.toLong()..30.toLong()
        ipManager.addBlackListRanges(listOf(range1, range2))
        Assertions.assertEquals(listOf(range1, range2), ipManager.getBlackList())
    }

    @Test
    fun `should add non-overlapping ranges in the right order`() {
        val range1 = 0.toLong()..10.toLong()
        val range2 = 20.toLong()..30.toLong()
        ipManager.addBlackListRanges(listOf(range2, range1))
        Assertions.assertEquals(listOf(range1, range2), ipManager.getBlackList())
    }

    @Test
    fun `should merge overlapping ranges`() {
        val range1 = 0.toLong()..15.toLong()
        val range2 = 10.toLong()..30.toLong()
        ipManager.addBlackListRanges(listOf(range1, range2))
        Assertions.assertEquals(listOf(0.toLong()..30.toLong()), ipManager.getBlackList())
    }

    @Test
    fun `should find lowest non blocked IP`() {
        val range1 = 0.toLong()..15.toLong()
        val range2 = 10.toLong()..30.toLong()
        val range3 = 35.toLong()..40.toLong()
        ipManager.addBlackListRanges(listOf(range1, range2, range3))
        Assertions.assertEquals(31, ipManager.getLowestWhiteIP())
    }

    @Test
    fun `should merge consecutive ranges`() {
        val range1 = 0.toLong()..15.toLong()
        val range2 = 16.toLong()..30.toLong()
        ipManager.addBlackListRanges(listOf(range1, range2))
        Assertions.assertEquals(listOf(0.toLong()..30.toLong()), ipManager.getBlackList())
    }

    @Test
    fun `should parse list`() {
        val input = listOf("5-8", "0-2", "4-7")
        ipManager.addBlackListRange(input)
        Assertions.assertEquals(3, ipManager.getLowestWhiteIP())
    }

    @Test
    fun `should parse second list`() {
        val input = listOf("5-8", "0-3", "4-7")
        ipManager.addBlackListRange(input)
        Assertions.assertEquals(9, ipManager.getLowestWhiteIP())
    }

    @Test
    fun `should find actual value`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2016/day20/input.txt").readLines()
        ipManager.addBlackListRange(input)
        Assertions.assertEquals(31053880, ipManager.getLowestWhiteIP())
    }

    @Test
    fun `should count blocked IP`() {
        val input = listOf("5-8", "0-2", "4-7")
        ipManager.addBlackListRange(input)
        Assertions.assertEquals(8, ipManager.countBlockedIP())
    }


    @Test
    fun `should count allowed IP`() {
        val input = listOf("5-8", "0-2", "4-7")
        ipManager.addBlackListRange(input)
        Assertions.assertEquals(4294967288, ipManager.countAllowedIP())
    }

    @Test
    fun `should count actually allowed IP`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2016/day20/input.txt").readLines()
        ipManager.addBlackListRange(input)
        Assertions.assertEquals(117, ipManager.countAllowedIP())
    }
}