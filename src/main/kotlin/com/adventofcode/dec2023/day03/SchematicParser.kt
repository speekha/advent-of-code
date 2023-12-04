package com.adventofcode.dec2023.day03

class SchematicParser(input: List<String>) {

    val parts = listParts(input)

    private fun listParts(input: List<String>): List<Int> {
        val numbers = input.flatMap {
            it.split("[^0-9]+".toRegex()).filter { it.isNotBlank() }.map { it.toInt() }
        }.filter { isPartEngine(it, input) }

        return numbers
    }

    private fun isPartEngine(part: Int, input: List<String>): Boolean {
        val value = part.toString()
        val row = input.indexOfFirst { it.contains(value) }
        val col = input[row].indexOf(value)
        if (row > 0 && input[row - 1].substring(
                (col - 1).coerceIn(input[row].indices)..(col + value.length + 1).coerceIn(
                    input[row].indices
                )
            ).any { it.isMarker() }
        )
            return true
        if (row < input.size - 1 && input[row + 1].substring(
                (col - 1).coerceIn(input[row].indices)..(col + value.length + 1).coerceIn(
                    input[row].indices
                )
            ).any { it.isMarker() }
        )
            return true
        if (col > 0 && input[row][col - 1].isMarker())
            return true
        if (col  + value.length < input[row].length - 1 && input[row][col + value.length].isMarker())
            return true

        return false
    }

    private fun Char.isMarker() = this != '.' && this !in '0'..'9'
}
