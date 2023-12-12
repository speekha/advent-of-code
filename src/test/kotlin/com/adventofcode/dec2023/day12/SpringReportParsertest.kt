package com.adventofcode.dec2023.day12

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SpringReportParsertest {

    val input = listOf(
                "???.### 1,1,3",
                ".??..??...?##. 1,1,3",
                "?#?#?#?#?#?#?#? 1,3,1,6",
                "????.#...#... 4,1,1",
                "????.######..#####. 1,6,5",
                "?###???????? 3,2,1"
    )

    @Test
    fun `should count combinations`() {
        val parser = SpringReportParser()
        assertEquals(1, parser.countArrangements(input[0]))
        assertEquals(4, parser.countArrangements(input[1]))
        assertEquals(10, parser.countArrangements(input.last()))
        assertEquals(21, input.sumOf { parser.countArrangements(it) })
    }

    @Test
    fun `should count actual combinations`() {
        val parser = SpringReportParser()
        assertEquals(8022, actualInputList.sumOf { parser.countArrangements(it) })
    }

    @Test
    fun `should count unfolded combinations`() {
        val parser = SpringReportParser()
        assertEquals(1, parser.countUnfoldedArrangements(input[0]))
        assertEquals(16384, parser.countUnfoldedArrangements(input[1]))
    }
    @Test
    fun `should count actual unfolded combinations`() {
        val parser = SpringReportParser()
        assertEquals(4968620679637, actualInputList.sumOf { parser.countUnfoldedArrangements(it) })
    }
}