package com.adventofcode.dec2016.day14

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class OneTimePadGeneratorTest {


    @Test
    fun `should have triplets in hash`() {
        val generator = HashGenerator()
        assertEquals("577571be4de9dcce85a041ba0410f29f", generator.simpleKey("abc0"))
        assertTrue(generator.simpleKey("abc18").contains("cc38887a5"))
        assertTrue(generator.simpleKey("abc39").contains("eee"))
    }

    @Test
    fun `should have quintuplet in hash for 816`() {
        val generator = HashGenerator()
        assertTrue(generator.simpleKey("abc816").contains("eeeee"))
    }

    @Test
    fun `0 should be an invalid hash`() {
        val input = "abc"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertFalse(generator.isHashValid(0))
    }

    @Test
    fun `18 should be an invalid hash`() {
        val input = "abc"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertFalse(generator.isHashValid(18))
    }

    @Test
    fun `39 should be a valid hash`() {
        val input = "abc"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertTrue(generator.isHashValid(39))
    }

    @Test
    fun `39 should produce the first key`() {
        val input = "abc"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertEquals(39, generator.generate(1))
    }

    @Test
    fun `92 should produce the second key`() {
        val input = "abc"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertEquals(92, generator.generate(2))
    }

    @Disabled
    @Test
    fun `22728 should produce the 64th key`() {
        val input = "abc"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertEquals(22728, generator.generate(64))
    }

    @Disabled
    @Test
    fun `23769 should produce the 64th key`() {
        val input = "cuanljph"
        val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
        assertEquals(23769, generator.generate(64))
    }

    @Test
    fun `should have triplets in stretched key`() {
        val generator = HashGenerator()
        assertEquals("a0107ff634856bb300138cac6568c0f24", generator.stretchedKey("abc0"))
    }

    @Test
    fun `should have quintuplet in stretched key`() {
        val generator = HashGenerator()
        assertTrue(generator.simpleKey("abc816").contains("eeeee"))
    }


}