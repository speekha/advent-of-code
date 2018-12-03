package com.adventofcode.dec2017.day1

class CaptchaBreaker {

    fun parseFirstCaptcha(input: String): Int = addPairs(input, 1)

    fun parseSecondCaptcha(input: String) = addPairs(input, input.length / 2)

    private fun addPairs(input: String, offset: Int) = input.toCharArray()
            .map { it - '0' }.run {
        indices.filter { this[it] == this[(it + offset) % this.size] }
                .sumBy { this[it] }
    }
}