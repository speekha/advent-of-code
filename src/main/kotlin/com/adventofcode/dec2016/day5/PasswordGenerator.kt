package com.adventofcode.dec2016.day5

import java.security.MessageDigest

class PasswordGenerator {
    fun hashString(message: String): String {
        val digest = MessageDigest.getInstance("MD5")
        return digest.digest(message.toByteArray())
                .map { String.format("%02x", it) }
                .joinToString("")
    }

    fun findSuitableHash(input: String): Int {
        var i = 0
        while (!hashString("$input$i").startsWith("00000")) {
            i++
        }
        return i
    }

    fun generatePassword(input: String): String {
        val password = StringBuilder()
        var i = 0
        var hash: String
        for (char in 0..7) {
            i = searchForNextHash(input, i)
            hash = hashString("$input$i")
            password.append(hash[5])
            println(password)
        }
        return password.toString()
    }

    fun generateComplexPassword(input: String): String {
        val password = Array<Char?>(8) { null }
        var i = 0
        var hash: String
        while (password.any { it == null }) {
            i = searchForNextHash(input, i)
            hash = hashString("$input$i")
            setPasswordDigit(hash, password)
        }
        return password.joinToString("")
    }

    private fun searchForNextHash(input: String, index: Int): Int {
        var i = index
        do {
            i++
        } while (!hashString("$input$i").startsWith("00000"))
        return i
    }

    private fun setPasswordDigit(hash: String, password: Array<Char?>) {
        val pos = hash[5]
        if (pos in '0'..'7' && password[pos - '0'] == null) {
            password[pos - '0'] = hash[6]
            println(hash[6])
        }
    }
}

fun main() {
    val input = "ffykfhsq"
    with(PasswordGenerator()) {
        //println("Password: ${generatePassword(input)}")
        println("Password: ${generateComplexPassword(input)}")
    }
}