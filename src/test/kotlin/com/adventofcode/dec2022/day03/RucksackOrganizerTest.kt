package com.adventofcode.dec2022.day03

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RucksackOrganizerTest {

    private val input = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw"
    )

    @Test
    fun `should parse rucksacks`() {
        val rucksack = Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp")
        assertEquals("vJrwpWtwJgWr", rucksack.pocket1)
        assertEquals("hcsFMMfFFhFp", rucksack.pocket2)
    }

    @Test
    fun `should find incorrect item`() {
        val rucksack = Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp")
        assertEquals('p', rucksack.findMistake())
    }

    @Test
    fun `should add priorities`() {
        val organizer = RucksackOrganizer()
        assertEquals(157, organizer.addPriorities(input))
    }

    @Test
    fun `should add actual priorities`() {
        val organizer = RucksackOrganizer()
        assertEquals(7980, organizer.addPriorities(actualInputList))
    }

    @Test
    fun `should identify group badge`() {
        val organizer = RucksackOrganizer()
        assertEquals('r', organizer.computeBadge(input.take(3)))
    }

    @Test
    fun `should add badge priorities`() {
        val organizer = RucksackOrganizer()
        assertEquals(70, organizer.computeBadgePriorities(input))
    }

    @Test
    fun `should add actual badge priorities`() {
        val organizer = RucksackOrganizer()
        assertEquals(2881, organizer.computeBadgePriorities(actualInputList))
    }
}