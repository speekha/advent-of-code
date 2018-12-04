package com.adventofcode.dec2020.day02

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PasswordDebuggerTest {

    private val debugger = PasswordDebugger()

    @Test
    fun `should find valid password for sled rental policies`() {
        assertTrue(debugger.isPawwordValid("abcde", "1-3 a", PasswordDebugger.Policy.SLED_RENTAL))
        assertFalse(debugger.isPawwordValid("cdefg", "1-3 b", PasswordDebugger.Policy.SLED_RENTAL))
        assertTrue(debugger.isPawwordValid("ccccccccc", "2-9 c", PasswordDebugger.Policy.SLED_RENTAL))
    }

    @Test
    fun `should count valid password for sled rental policies`() {
        val input = listOf("1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc")
        assertEquals(2, debugger.countValidPawword(input, PasswordDebugger.Policy.SLED_RENTAL))
    }

    @Test
    fun `should count valid passwords for sled rental policies in actual data`() {
        val input = readInputAsList()
        assertEquals(569, debugger.countValidPawword(input, PasswordDebugger.Policy.SLED_RENTAL))
    }

    @Test
    fun `should find valid password for toboggan rental policies`() {
        assertTrue(debugger.isPawwordValid("abcde", "1-3 a", PasswordDebugger.Policy.TOBOGGAN_RENTAL))
        assertFalse(debugger.isPawwordValid("cdefg", "1-3 b", PasswordDebugger.Policy.TOBOGGAN_RENTAL))
        assertFalse(debugger.isPawwordValid("ccccccccc", "2-9 c", PasswordDebugger.Policy.TOBOGGAN_RENTAL))
    }

    @Test
    fun `should count valid password for toboggan rental policies`() {
        val input = listOf("1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc")
        assertEquals(1, debugger.countValidPawword(input, PasswordDebugger.Policy.TOBOGGAN_RENTAL))
    }

    @Test
    fun `should count valid passwords for toboggan rental policies in actual data`() {
        val input = readInputAsList()
        assertEquals(346, debugger.countValidPawword(input, PasswordDebugger.Policy.TOBOGGAN_RENTAL))
    }
}