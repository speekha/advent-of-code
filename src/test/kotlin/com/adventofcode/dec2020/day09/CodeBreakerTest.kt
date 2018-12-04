package com.adventofcode.dec2020.day09

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CodeBreakerTest {

    val input = listOf(
        "35",
        "20",
        "15",
        "25",
        "47",
        "40",
        "62",
        "55",
        "65",
        "95",
        "102",
        "117",
        "150",
        "182",
        "127",
        "219",
        "299",
        "277",
        "309",
        "576"
    )

    @Test
    fun `should find erroneous number`() {
        val breaker = CodeBreaker()
        Assertions.assertEquals(127, breaker.findError(input.map { it.toLong() }, 5))
    }

    @Test
    fun `should find erroneous number in actual data`() {
        val breaker = CodeBreaker()
        Assertions.assertEquals(23278925, breaker.findError(readInputAsList().map { it.toLong() }, 25))
    }

    @Test
    fun `should find XMAS weakness`() {
        val breaker = CodeBreaker()
        Assertions.assertEquals(62, breaker.findWeakness(input.map { it.toLong() }, 5))
    }


    @Test
    fun `should find XMAS weakness in actual data`() {
        val breaker = CodeBreaker()
        Assertions.assertEquals(4011064, breaker.findWeakness(readInputAsList().map { it.toLong() }, 25))
    }
}