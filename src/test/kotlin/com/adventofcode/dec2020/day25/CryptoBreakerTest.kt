package com.adventofcode.dec2020.day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CryptoBreakerTest {

    val cardKey = 5764801L
    val doorKey = 17807724L

    val actualData = Pair(16915772L, 18447943L)

    @Test
    fun `should produce public keys`() {
        val crypto = CryptoBreaker()
        assertEquals(cardKey, crypto.generateKey(8))
        assertEquals(doorKey, crypto.generateKey(11))
    }

    @Test
    fun `should break keys`() {
        val crypto = CryptoBreaker()
        assertEquals(8, crypto.breakKey(cardKey).second)
        assertEquals(11, crypto.breakKey(doorKey).second)
    }

    @Test
    fun `should produce encryption key`() {
        val crypto = CryptoBreaker()
        assertEquals(14897079L, crypto.buildEncryptionKey(cardKey, doorKey))
    }

    @Test
    fun `should produce actual encryption key`() {
        val (card, door) = actualData
        val crypto = CryptoBreaker()
        assertEquals(6011069L, crypto.buildEncryptionKey(card, door))
    }
}