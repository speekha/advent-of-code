package com.adventofcode.dec2023.day03

class SchematicParser(input: List<String>) {

    val parts = listParts(input)

    private fun listParts(input: List<String>): List<Part> {
        val numbers = input.indices.flatMap { index ->
            findNumbers(input, index)
        }
        return numbers
    }

    private fun findNumbers(input: List<String>, row: Int): List<Part> {
        val pattern = "\\d+".toRegex()
        val rowStr = input[row]
        var i = 0
        val result = mutableListOf<Part>()
        while (i != -1) {
            val find = pattern.find(rowStr, i)
            if (find != null) {
                val range = find.range
                val value = find.value
                val part =Part(value, row, range, extractSurroundings(row, range, input))
                if (part.isEnginePart()) {
                    result += part
                }
                i = range.last + 1
            } else {
                i = -1
            }
        }
        return result
    }

    private fun extractSurroundings(row: Int, range: IntRange, input: List<String>): List<String> {
        val rows = ((row - 1).coerceAtLeast(0)..(row + 1).coerceAtMost(input.lastIndex)).map { input[it] }
        return rows.map {
            it.substring((range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(it.lastIndex))
        }
    }

    fun computeGearRatios(): Int {
        val gears = parts.filter { it.type == '*' }.groupBy { it.gearCoordinates }.filter { it.value.size == 2 }
        return gears.values.sumOf { it[0].id.toInt() * it[1].id.toInt() }
    }
}
