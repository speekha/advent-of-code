package com.adventofcode.dec2016.day6

import org.junit.Assert.assertEquals
import org.junit.Test

class MessageErrorCorrectorTest {

    @Test
    fun `should be test`() {
        val input = listOf("test",
                "test")
        assertEquals("test", MessageErrorCorrector().restoreMessage(input))
    }

    @Test
    fun `should be easter`() {
        val input = listOf("eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar")
        assertEquals("easter", MessageErrorCorrector().restoreMessage(input))
    }

    @Test
    fun `should be advent`() {
        val input = listOf("eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar")
        assertEquals("advent", MessageErrorCorrector().restoreMessageWithObfuscation(input))
    }
}