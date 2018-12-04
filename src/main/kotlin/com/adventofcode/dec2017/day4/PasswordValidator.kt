package com.adventofcode.dec2017.day4

import java.io.File

class PasswordValidator {
    fun isPasswordValid(input: String) = input.split(" ").groupBy { it }.none { it.value.size > 1 }

    fun countValidPasswords(input: List<String>) = input.count { isPasswordValid(it) }

    fun isPasswordSuperSafe(input: String) = input.split(" ")
            .groupBy {
                it.toCharArray()
                        .sorted()
                        .joinToString("")
            }
            .none { it.value.size > 1 }

    fun countSuperSafePasswords(input: List<String>) = input.count { isPasswordSuperSafe(it) }
}


fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day4/input.txt").readLines()
    println("Testing : ${input.size}")
    with(PasswordValidator()) {
        println("Valid passwords: ${countValidPasswords(input)}")
        println("Super safe passwords: ${countSuperSafePasswords(input)}")
    }
}