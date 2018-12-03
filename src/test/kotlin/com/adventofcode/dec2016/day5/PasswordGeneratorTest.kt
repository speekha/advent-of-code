package com.adventofcode.dec2016.day5

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Ignore
import org.junit.Test

class PasswordGeneratorTest {

    @Test
    fun `should start with 00000`() {
        assertThat(PasswordGenerator().hashString("abc3231929"), CoreMatchers.startsWith("00000"))
    }

    @Ignore
    @Test
    fun `first index should be 3231929`() {
        assertEquals(3231929, PasswordGenerator().findSuitableHash("abc"))
    }

    @Ignore
    @Test
    fun `password should be 18f47a30`() {
        assertEquals("18f47a30", PasswordGenerator().generatePassword("abc"))
    }

    @Ignore
    @Test
    fun `complex password should be 05ace8e3`() {
        assertEquals("05ace8e3", PasswordGenerator().generateComplexPassword("abc"))
    }


}