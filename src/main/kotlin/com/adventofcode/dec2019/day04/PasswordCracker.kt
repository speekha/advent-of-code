package com.adventofcode.dec2019.day04

class PasswordCracker {
    fun isValid(password: String): Boolean =
            password.drop(1).withIndex().all { (index, char) -> char >= password[index] }
                    && password.drop(1).withIndex().any { (index, char) -> char == password[index] }

    fun isReallyValid(password: String): Boolean {
        return password.drop(1).withIndex().all { (index, char) -> char >= password[index] }
                && password.drop(1).withIndex().any { (index, char) ->
            char == password[index]
                    && (index == 0 || char != password[index - 1])
                    && (index == 4 || char != password[index + 2])
        }
    }

}
