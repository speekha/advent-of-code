package com.adventofcode.dec2021.day06

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LanternFishTest {

    val input = "3,4,3,1,2"

    @Test
    fun `should count down`() {
        val fish = LanternFish(3)
        fish.advanceDate()
        assertEquals(2, fish.countDown)
    }

    @Test
    fun `should parse shoal`() {
        val shoal = FishShoal(input)
    }

    @Test
    fun `should iterate population`() {
        val shoal = FishShoal(input)
        shoal.advanceDate(80)
        assertEquals(5934, shoal.size)
    }

    @Test
    fun `should iterate population longer`() {
        val shoal = FishShoal(input)
        shoal.advanceDate(256)
        assertEquals(26984457539, shoal.size)
    }

    @Test
    fun `should iterate actual population`() {
        val shoal = FishShoal(actualInputList[0])
        shoal.advanceDate(80)
        assertEquals(371379, shoal.size)
    }

    @Test
    fun `should iterate actual population longer`() {
        val shoal = FishShoal(actualInputList[0])
        shoal.advanceDate(256)
        assertEquals(1674303997472, shoal.size)
    }
}