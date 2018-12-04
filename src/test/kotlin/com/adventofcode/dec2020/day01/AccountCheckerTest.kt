package com.adventofcode.dec2020.day01

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AccountCheckerTest {

    @Test
    fun `should find accounting error`() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        val checker = AccountChecker(input)
        assertEquals(514579, checker.computeError(2))
    }

    @Test
    fun `should find 2 factors in actual data`() {
        val input = readInputAsList().map { it.toInt() }
        val checker = AccountChecker(input)
        assertEquals(902451, checker.computeError(2))
    }

    @Test
    fun `should find accounting error with three factors`() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        val checker = AccountChecker(input)
        assertEquals(241861950, checker.computeError(3))
    }

    @Test
    fun `should find 3 factors in actual data`() {
        val input = readInputAsList().map { it.toInt() }
        val checker = AccountChecker(input)
        assertEquals(85555470, checker.computeError(3))
    }


}