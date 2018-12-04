package com.adventofcode.dec2020.day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RowParserTest {

    @Test
    fun `should parse seat position`() {
        val input = "FBFBBFFRLR"
        val parser = RowParser()
        assertEquals(RowParser.Seat(44, 5), parser.parse(input))
    }

    @Test
    fun `should parse seat ID`() {
        val input = "FBFBBFFRLR"
        val parser = RowParser()
        assertEquals(357, parser.parse(input).id)
    }
}