package com.adventofcode.dec2022.day04

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AssignmentTest {

    val input = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8"
    )

    @Test
    fun `should find complete overlaps`() {
        assertFalse(Assignment(input[0]).isCompleteOverlap())
        assertTrue(Assignment(input[3]).isCompleteOverlap())
    }

    @Test
    fun `should count complete overlaps`() {
        assertEquals(2, input.count { Assignment(it).isCompleteOverlap() })
    }

    @Test
    fun `should count actual complete overlaps`() {
        assertEquals(424, actualInputList.count { Assignment(it).isCompleteOverlap() })
    }

    @Test
    fun `should find partial overlaps`() {
        assertFalse(Assignment(input[0]).isPartialOverlap())
        assertTrue(Assignment(input[3]).isPartialOverlap())
    }

    @Test
    fun `should count partial overlaps`() {
        assertEquals(4, input.count { Assignment(it).isPartialOverlap() })
    }

    @Test
    fun `should count actual partial overlaps`() {
        assertEquals(804, actualInputList.count { Assignment(it).isPartialOverlap() })
    }
}