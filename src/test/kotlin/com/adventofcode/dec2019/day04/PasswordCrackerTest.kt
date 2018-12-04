package com.adventofcode.dec2019.day04

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class PasswordCrackerTest {

    @Test
    fun `should validate if password meets criteria`() {
        val cracker = PasswordCracker()
        assertTrue(cracker.isValid("111111"))
        assertFalse(cracker.isValid("223450"))
        assertFalse(cracker.isValid("123789"))

    }

    @Test
    fun `should validate if password meets all criteria`() {
        val cracker = PasswordCracker()
        assertTrue(cracker.isReallyValid("112233"))
        assertFalse(cracker.isReallyValid("123444"))
        assertTrue(cracker.isReallyValid("111122"))
    }

    @Test
    fun `should count valid passwords in range`() {
        val cracker = PasswordCracker()
        val count = (284639..748759).count { cracker.isValid(it.toString()) }
        assertEquals(895, count)
    }

    @Test
    fun `should count really valid passwords in range`() {
        val cracker = PasswordCracker()
        val count = (284639..748759).count { cracker.isReallyValid(it.toString()) }
        assertEquals(591, count)
    }
}