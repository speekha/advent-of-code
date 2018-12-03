package com.adventofcode.dec2017.day4

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class PasswordValidatorTest {

    @Test
    fun `check valid passwords`() {
        assertTrue(PasswordValidator().isPasswordValid("aa bb cc dd ee"))
        assertTrue(PasswordValidator().isPasswordValid("aa bb cc dd aaa"))
    }

    @Test
    fun `check invalid passwords`() {
        assertFalse(PasswordValidator().isPasswordValid("aa bb cc dd aa"))
    }

    @Test
    fun `count valid password`() {
        val input = "aa bb cc dd ee"
        assertEquals(1, PasswordValidator().countValidPasswords(input.split("\n")))
    }

    @Test
    fun `count valid passwords`() {
        val input = "aa bb cc dd ee\n" +
                "aa bb cc dd aa\n" +
                "aa bb cc dd aaa"
        assertEquals(2, PasswordValidator().countValidPasswords(input.split("\n")))
    }

    @Test
    fun `check supersafe passwords`() {
        assertTrue(PasswordValidator().isPasswordSuperSafe("abcde fghij"))
        assertTrue(PasswordValidator().isPasswordSuperSafe("a ab abc abd abf abj"))
        assertTrue(PasswordValidator().isPasswordSuperSafe("iiii oiii ooii oooi oooo"))
    }

    @Test
    fun `check unsafe passwords`() {
        assertFalse(PasswordValidator().isPasswordSuperSafe("abcde xyz ecdab"))
        assertFalse(PasswordValidator().isPasswordSuperSafe("oiii ioii iioi iiio"))
    }

    @Test
    fun `count supersafe passwords`() {
        val input = "abcde fghij\n" +
                "a ab abc abd abf abj\n" +
                "iiii oiii ooii oooi oooo\n" +
                "abcde xyz ecdab\n" +
                "oiii ioii iioi iiio"
        assertEquals(3, PasswordValidator().countSuperSafePasswords(input.split("\n")))
    }

    @Test
    fun `test real values`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day4/input.txt").readLines()
        with(PasswordValidator()) {
            assertEquals(455, countValidPasswords(input))
            assertEquals(186, countSuperSafePasswords(input))
        }
    }
}