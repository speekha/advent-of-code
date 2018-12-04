package com.adventofcode.dec2020.day02

class PasswordDebugger {
    fun isPawwordValid(password: String, rules: String, policy: Policy): Boolean {
        val (indexes, char) = rules.split(" ")
        val (min, max) = indexes.split("-").map { it.toInt() }
        return when (policy) {
            Policy.SLED_RENTAL -> password.count { it == char[0] } in min..max
            Policy.TOBOGGAN_RENTAL -> (password[min - 1] == char[0]) xor (password[max - 1] == char[0])
        }
    }

    fun countValidPawword(inputs: List<String>, policy: Policy): Int = inputs.count {
        val (rules, password) = it.split(": ")
        isPawwordValid(password, rules, policy)
    }

    enum class Policy {
        SLED_RENTAL, TOBOGGAN_RENTAL
    }
}
