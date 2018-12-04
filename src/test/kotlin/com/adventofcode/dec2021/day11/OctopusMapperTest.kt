package com.adventofcode.dec2021.day11

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OctopusMapperTest {

    val input = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )

    @Test
    fun `should load octopus map`() {
        val mapper = OctopusMapper(input)
        assertEquals(input.joinToString("\n"), mapper.getState())
    }

    @Test
    fun `should iterate one step`() {
        val mapper = OctopusMapper(input)
        mapper.iterate()
        assertEquals(
            "6594254334\n" +
                    "3856965822\n" +
                    "6375667284\n" +
                    "7252447257\n" +
                    "7468496589\n" +
                    "5278635756\n" +
                    "3287952832\n" +
                    "7993992245\n" +
                    "5957959665\n" +
                    "6394862637", mapper.getState()
        )
    }

    @Test
    fun `should iterate two steps`() {
        val mapper = OctopusMapper(input)
        repeat(2) {
            mapper.iterate()
        }
        assertEquals(
            "8807476555\n" +
                    "5089087054\n" +
                    "8597889608\n" +
                    "8485769600\n" +
                    "8700908800\n" +
                    "6600088989\n" +
                    "6800005943\n" +
                    "0000007456\n" +
                    "9000000876\n" +
                    "8700006848", mapper.getState()
        )
    }

    @Test
    fun `should iterate three steps`() {
        val mapper = OctopusMapper(input)
        repeat(3) {
            mapper.iterate()
        }
        assertEquals(
            "0050900866\n" +
                    "8500800575\n" +
                    "9900000039\n" +
                    "9700000041\n" +
                    "9935080063\n" +
                    "7712300000\n" +
                    "7911250009\n" +
                    "2211130000\n" +
                    "0421125000\n" +
                    "0021119000", mapper.getState()
        )
    }

    @Test
    fun `should count flashes for 10 steps`() {
        val mapper = OctopusMapper(input)
        val flashes = (0..9).sumOf {
            mapper.iterate()
        }
        assertEquals(204, flashes)
    }

    @Test
    fun `should count flashes for 100 steps`() {
        val mapper = OctopusMapper(input)
        val flashes = (0..99).sumOf {
            mapper.iterate()
        }
        assertEquals(1656, flashes)
    }

    @Test
    fun `should count actual flashes for 100 steps`() {
        val mapper = OctopusMapper(actualInputList)
        val flashes = (0..99).sumOf {
            mapper.iterate()
        }
        assertEquals(1599, flashes)
    }

    @Test
    fun `should find first synchronized flash`() {
        val mapper = OctopusMapper(input)
        assertEquals(195, mapper.findSimultaneousFlash())
    }

    @Test
    fun `should find first actual synchronized flash`() {
        val mapper = OctopusMapper(actualInputList)
        assertEquals(418, mapper.findSimultaneousFlash())
    }
}