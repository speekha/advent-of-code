package com.adventofcode.dec2016.day7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IPv7AnalyzerTest {

    @Test
    fun `should isolate non hypernet parts`() {
        val input = "abba[mnop]qrst"
        val result = listOf("abba", "qrst")
        assertEquals(result, IPv7Analyzer().extractSupernetParts(input))
    }

    @Test
    fun `should isolate multiple non hypernet parts`() {
        val input = "abba[mnop]qrst[mnop]qrst"
        val result = listOf("abba", "qrst", "qrst")
        assertEquals(result, IPv7Analyzer().extractSupernetParts(input))
    }

    @Test
    fun `should isolate hypernet parts`() {
        val input = "abba[mnop]qrst"
        assertEquals(listOf("mnop"), IPv7Analyzer().extractHypernetParts(input))
    }

    @Test
    fun `should isolate multiple hypernet parts`() {
        val input = "abba[mnop]qrst[mnop]"
        assertEquals(listOf("mnop", "mnop"), IPv7Analyzer().extractHypernetParts(input))
    }

    @Test
    fun `should have ABBA`() {
        val input = "zaeettelkj"
        assertTrue(IPv7Analyzer().hasABBAnnotation(input))
    }

    @Test
    fun `should be TLS address`() {
        val input = "abba[mnop]qrst"
        assertTrue(IPv7Analyzer().hasTlsSupport(input))
    }

    @Test
    fun `should not be TLS address`() {
        val input = "abcd[bddb]xyyx"
        assertFalse(IPv7Analyzer().hasTlsSupport(input))
    }

    @Test
    fun `count TLS addresses`() {
        val input = listOf("abba[mnop]qrst", "ioxxoj[asdfgh]zxcvbn", "abcd[bddb]xyyx", "aaaa[qwer]tyui")
        assertEquals(2, IPv7Analyzer().countIPv7WithTLS(input))
    }

    @Test
    fun `extract ABA`() {
    val input = "azsaabapjpoi"
        assertEquals(listOf("aba", "pjp"), IPv7Analyzer().extractABA(input))
    }

    @Test
    fun `has SSL support`() {
        val input = "aba[bab]xyz"
        assertTrue(IPv7Analyzer().hasSslSupport(input))
    }

    @Test
    fun `count SSL addresses`() {
        val input = listOf("aba[bab]xyz", "xyx[xyx]xyx", "aaa[kek]eke", "zazbz[bzb]cdb")
        assertEquals(3, IPv7Analyzer().countIPv7WithSSL(input))
    }


}