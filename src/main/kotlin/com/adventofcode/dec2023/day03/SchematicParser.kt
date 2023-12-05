package com.adventofcode.dec2023.day03

class SchematicParser(input: List<String>) {

    val parts = listParts(input)

    private fun listParts(input: List<String>): List<Int> {
        val numbers = input.indices.flatMap { index ->
            println("")
            findNumbers(input, index)
        }
        return numbers
    }

    private fun findNumbers(input: List<String>, row: Int): List<Int> {
        val pattern = "\\d+".toRegex()
        val rowStr = input[row]
        var i = 0
        val result = mutableListOf<Int>()
        while (i != -1) {
            val find = pattern.find(rowStr, i)
            if (find != null) {
                val range = find.range
                val value = find.value
                if (isPartEngine(value, row, range.first, input)) {
                    result += value.toInt()
                }
                i = range.last + 1
            } else {
                i = -1
            }
        }
        return result
    }

    private fun isPartEngine(value: String, row: Int, col: Int, input: List<String>): Boolean {
        val result = (row > 0 && input[row - 1].substring((col - 1).coerceIn(input[row].indices)..(col + value.length + 1).coerceIn(input[row].indices)).any { it.isMarker() })
                || (row < input.size - 1 && input[row + 1].substring((col - 1).coerceIn(input[row].indices)..(col + value.length).coerceIn(input[row].indices)).any { it.isMarker() })
                || (col > 0 && input[row][col - 1].isMarker())
                || (col + value.length < input[row].length - 1 && input[row][col + value.length].isMarker())
        if (!result) {
            print("$value\t")
        }
        return result
    }

    private fun Char.isMarker() = this != '.' && this !in '0'..'9'
}
