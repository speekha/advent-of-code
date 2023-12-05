package com.adventofcode.dec2023.day05

import com.adventofcode.actualInputList
import kotlin.test.Test
import kotlin.test.assertEquals

class FarmOptimizerTest {

    val input =
        """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent().split("\n")

    @Test
    fun `should save list of seeds`() {
        val almanac = Almanac(input)
        assertEquals(listOf(79L, 14L, 55L, 13L), almanac.seeds)
    }

    @Test
    fun `should find soils for seeds`() {
        val almanac = Almanac(input)
        assertEquals(81L, almanac.getSoilForSeed(79L))
        assertEquals(50L, almanac.getSoilForSeed(98L))
        assertEquals(99L, almanac.getSoilForSeed(97L))
    }

    @Test
    fun `should find location for seeds`() {
        val almanac = Almanac(input)
        assertEquals(82L, almanac.getLocationForSeed(79L))
        assertEquals(46L, almanac.getLocationForSeed(82L))
        assertEquals(13L, almanac.getSeedForLocation(35L))
    }

    @Test
    fun `should find best location for seeds`() {
        val almanac = Almanac(input)
        assertEquals(35L, almanac.getBestLocation())
    }

    @Test
    fun `should find best actual location for seeds`() {
        val almanac = Almanac(actualInputList)
        assertEquals(579439039, almanac.getBestLocation())
    }


    @Test
    fun `should find best location for seed ranges`() {
        val almanac = Almanac(input)
        assertEquals(46L, almanac.getBestLocationForSeedRanges())
    }


    @Test
    fun `should find best actual location for seed ranges`() {
        val almanac = Almanac(actualInputList)
        // < 7873085
        assertEquals(7873084L, almanac.getBestLocationForSeedRanges())
    }

}