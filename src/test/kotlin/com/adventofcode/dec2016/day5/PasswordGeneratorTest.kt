package com.adventofcode.dec2016.day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class PasswordGeneratorTest {

    @Test
    fun `should start with 00000`() {
        assertTrue(PasswordGenerator().hashString("abc3231929").startsWith("00000"))
    }

    @Disabled
    @Test
    fun `first index should be 3231929`() {
        assertEquals(3231929, PasswordGenerator().findSuitableHash("abc"))
    }

    @Disabled
    @Test
    fun `password should be 18f47a30`() {
        assertEquals("18f47a30", PasswordGenerator().generatePassword("abc"))
    }

    @Disabled
    @Test
    fun `complex password should be 05ace8e3`() {
        assertEquals("05ace8e3", PasswordGenerator().generateComplexPassword("abc"))
    }


}