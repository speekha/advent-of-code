package com.adventofcode.dec2020.day16

class TicketValidator(input: List<String>) {

    val fieldRules = input.takeWhile { it.isNotEmpty() }.map { parseFieldRule(it) }

    val myTicket: Ticket = parseTicket(input[fieldRules.size + 2])

    val nearbyTickets = input.drop(fieldRules.size + 5).map { parseTicket(it) }

    val validTickets = nearbyTickets.filter { it.isValid(fieldRules) }

    data class Field(
        val name: String,
        val firstRange: IntRange,
        val secondRange: IntRange
    ) {
        operator fun contains(value: Int): Boolean = value in firstRange || value in secondRange
    }

    private fun parseFieldRule(input: String): Field {
        val (name, ranges) = input.split(": ")
        val (firstRange, secondRange) = ranges.split(" or ")
        return Field(name, firstRange.toRange(), secondRange.toRange())
    }

    private fun String.toRange(): IntRange {
        val (min, max) = this.split("-").map { it.toInt() }
        return min..max
    }

    data class Ticket(val fields: List<Int>) {
        fun sumInvalidFields(rules: List<Field>) = fields.sumOf {
            it.takeIf { field -> rules.none { field in it } } ?: 0
        }

        fun isValid(rules: List<Field>) =
            fields.all { field -> rules.any { field in it } }
    }

    private fun parseTicket(input: String) =
        Ticket(input.split(",").map { it.toInt() })

    fun computeErrorRate(): Int = nearbyTickets.sumOf {
        it.sumInvalidFields(fieldRules)
    }

    fun findField(field: String): Int = findFields()[field] ?: error("Field not found")

    fun findFields(prefix: String): Map<String, Int> = findFields().filter { it.key.startsWith(prefix) }

    private fun findFields(): Map<String, Int> {
        val result = myTicket.fields.indices.map { i ->
            fieldRules.filter { rule -> validTickets.all { it.fields[i] in rule } }.map { it.name }.toMutableList()
        }
        val fields = mutableMapOf<String, Int>()
        while (fields.size < fieldRules.size) {
            result.forEachIndexed { index, value ->
                if (value.size == 1) {
                    fields[value[0]] = index
                }
                value.removeAll(fields.keys)
            }
        }
        return fields
    }
}