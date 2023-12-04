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
        var digit1 = -1
        var digit2 = -1
        var i = 0
        while (i < input.length) {
            if (input[i] in '0'..'9') {
                digit1 = input[i].digitToInt()
                break
            } else {
                val digit = digitStrings.firstOrNull { input.drop(i).startsWith(it) }
                if (digit != null) {
                    digit1 = digitStrings.indexOf(digit)
                    break
                } else {
                    i++
                }
            }
        }
        i = input.length - 1
        while (i >= 0) {
            if (input[i] in '0'..'9') {
                digit2 = input[i].digitToInt()
                break
            } else {
                val digit = digitStrings.firstOrNull { input.drop(i).startsWith(it) }
                if (digit != null) {
                    digit2 = digitStrings.indexOf(digit)
                    break
                } else {
                    i--
                }
            }
        }

        return digit1 * 10 + digit2
    }

    private fun mapDigits(inputValue: String): String {
        var mapped = inputValue
        while (digitStrings.any { mapped.indexOf(it) != -1 }) {
            val digit = digitStrings.map { string -> mapped.indexOf(string) to string }.filter { it.first != -1 }
                .minBy { it.first }.second
            mapped = mapped.replace(digit, digitStrings.indexOf(digit).toString())
        }

        return mapped
    }


}

