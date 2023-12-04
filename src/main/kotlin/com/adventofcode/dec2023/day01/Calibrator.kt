package com.adventofcode.dec2023.day01

class Calibrator {

    private val digitStrings = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    fun calibrate(input: List<String>): Int = input.sumOf {
        val digits = it.filter { it in '0'..'9' }.map { (it - '0').toInt() }
        10 * digits.first() + digits.last()
    }

    fun advancedCalibration(input: List<String>): Int = input.sumOf { inputValue ->
        computeValue(inputValue)
    }

    private fun computeValue(input: String): Int {
        val digit1 = findFirstDigit(input, 0, 1)
        val digit2 = findFirstDigit(input, input.length - 1, -1)
        return digit1 * 10 + digit2
    }

    private fun findFirstDigit(input: String, start: Int, increment: Int): Int {
        var digit = -1
        var i = start
        while (i in input.indices) {
            if (input[i] in '0'..'9') {
                digit = input[i].digitToInt()
                break
            } else {
                val j = digitStrings.firstOrNull { input.drop(i).startsWith(it) }
                if (j != null) {
                    digit = digitStrings.indexOf(j)
                    break
                } else {
                    i += increment
                }
            }
        }
        return digit
    }


}

