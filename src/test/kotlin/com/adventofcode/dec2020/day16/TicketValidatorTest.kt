package com.adventofcode.dec2020.day16

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketValidatorTest {

    val input = listOf(
        "class: 1-3 or 5-7",
        "row: 6-11 or 33-44",
        "seat: 13-40 or 45-50",
        "",
        "your ticket:",
        "7,1,14",
        "",
        "nearby tickets:",
        "7,3,47",
        "40,4,50",
        "55,2,20",
        "38,6,12"
    )

    @Test
    fun `should extract input categories`() {
        val validator = TicketValidator(input)
        assertEquals(4, validator.nearbyTickets.size)
    }

    @Test
    fun `should parse field rules`() {
        val validator = TicketValidator(input)
        assertEquals(
            listOf(
                TicketValidator.Field("class", 1..3, 5..7),
                TicketValidator.Field("row", 6..11, 33..44),
                TicketValidator.Field("seat", 13..40, 45..50)
            ), validator.fieldRules
        )
    }

    @Test
    fun `should parse my ticket`() {
        val validator = TicketValidator(input)
        assertEquals(TicketValidator.Ticket(listOf(7, 1, 14)), validator.myTicket)
    }

    @Test
    fun `should parse nearby tickets`() {
        val validator = TicketValidator(input)
        assertEquals(
            listOf(
                TicketValidator.Ticket(listOf(7, 3, 47)),
                TicketValidator.Ticket(listOf(40, 4, 50)),
                TicketValidator.Ticket(listOf(55, 2, 20)),
                TicketValidator.Ticket(listOf(38, 6, 12))
            ), validator.nearbyTickets
        )
    }

    @Test
    fun `should compute error rate`() {
        val validator = TicketValidator(input)
        assertEquals(71, validator.computeErrorRate())
    }

    @Test
    fun `should filter valid tickets`() {
        val validator = TicketValidator(input)
        assertEquals(
            listOf(
                TicketValidator.Ticket(listOf(7, 3, 47))
            ), validator.validTickets
        )
    }

    @Test
    fun `should compute actual error rate`() {
        val validator = TicketValidator(readInputAsList())
        assertEquals(25916, validator.computeErrorRate())
    }

    @Test
    fun `should find fields`() {
        val input = listOf(
            "class: 0-1 or 4-19",
            "row: 0-5 or 8-19",
            "seat: 0-13 or 16-19",
            "",
            "your ticket:",
            "11,12,13",
            "",
            "nearby tickets:",
            "3,9,18",
            "15,1,5",
            "5,14,9"
        )
        val validator = TicketValidator(input)
        assertEquals(0, validator.findField("row"))
        assertEquals(1, validator.findField("class"))
        assertEquals(2, validator.findField("seat"))
    }

    @Test
    fun `should find departure fields`() {
        val validator = TicketValidator(readInputAsList())
        val departureFields = validator.findFields("departure")
        assertEquals(2564529489989, departureFields
            .values
            .map { validator.myTicket.fields[it].toLong() }
            .reduce { acc, i -> acc * i }
        )
    }
}