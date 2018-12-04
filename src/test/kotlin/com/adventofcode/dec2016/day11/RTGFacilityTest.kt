package com.adventofcode.dec2016.day11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RTGFacilityTest {

    val input = listOf("The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.",
            "The second floor contains a hydrogen generator.",
            "The third floor contains a lithium generator.",
            "The fourth floor contains nothing relevant.")

    @Test
    fun `elevator should start at floor one`() {
        val facility = RTGFacility(input)
        assertEquals(0, facility.elevator)
    }

    @Test
    fun `fourth floor should be empty`() {
        assertTrue(RTGFacility.parseFloor(input[3]).isEmpty())
    }

    @Test
    fun `third floor should have a lithium generator`() {
        assertEquals(setOf(Item("lithium", ItemType.GENERATOR)), RTGFacility.parseFloor(input[2]).toSet())
    }

    @Test
    fun `second floor should have a hydrogen generator`() {
        assertEquals(setOf(Item("hydrogen", ItemType.GENERATOR)), RTGFacility.parseFloor(input[1]).toSet())
    }

    @Test
    fun `first floor should have two microchips`() {
        val facility = RTGFacility(input)
        assertEquals(setOf(
                Item("hydrogen", ItemType.MICROCHIP),
                Item("lithium", ItemType.MICROCHIP)
        ), facility.floors[0].toSet())
    }

    @Test
    fun `should be valid move`() {
        val facility = RTGFacility(input)
        assertTrue(facility.isValidMove(Move(1, setOf(Item("hydrogen", ItemType.MICROCHIP)))))
    }

    @Test
    fun `should be invalid move`() {
        val facility = RTGFacility(input)
        assertFalse(facility.isValidMove(Move(-1, setOf(Item("hydrogen", ItemType.MICROCHIP)))))
        assertFalse(facility.isValidMove(Move(1, setOf(Item("lithium", ItemType.MICROCHIP)))))
    }

    @Test
    fun `first move should be up with HM`() {
        val facility = RTGFacility(input)
        assertEquals(setOf(Move(1, setOf(Item("hydrogen", ItemType.MICROCHIP)))), facility.getPossibleMoves().toSet())
    }

    @Test
    fun `second move should be up with HM`() {
        val facility = RTGFacility(input).move(Move(1, setOf(Item("hydrogen", ItemType.MICROCHIP))))
        assertEquals(setOf(
                Move(1, setOf(
                        Item("hydrogen", ItemType.MICROCHIP),
                        Item("hydrogen", ItemType.GENERATOR)
                )),
                Move(1, setOf(
                        Item("hydrogen", ItemType.GENERATOR)
                )),
                Move(-1, setOf(
                        Item("hydrogen", ItemType.MICROCHIP)
                ))
                ), facility.getPossibleMoves().toSet())
    }

    @Test
    fun `check signature`() {
        val facility = RTGFacility(input)
        assertEquals("0/HMLM/HG/LG/", facility.hash)
    }

    @Test
    fun `find shortest path`() {
        val facility = RTGFacility(input)
        assertEquals(11, RTGFacility.moveEveryThing(facility))
    }
}