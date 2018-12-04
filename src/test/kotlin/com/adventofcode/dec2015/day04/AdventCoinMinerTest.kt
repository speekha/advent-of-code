package com.adventofcode.dec2015.day04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class AdventCoinMinerTest {

    @Test
    @Disabled("A bit long")
    fun `should mine coins`() {
        val input = "abcdef"
        val miner = AdventCoinMiner()
        assertEquals(609043, miner.findHash(input, 5))
    }

    @Test
    @Disabled("A bit long")
    fun `should mine coins with actual hash`() {
        val input = "ckczppom"
        val miner = AdventCoinMiner()
        assertEquals(117946, miner.findHash(input, 5))
        assertEquals(3938038, miner.findHash(input, 6))
    }
}