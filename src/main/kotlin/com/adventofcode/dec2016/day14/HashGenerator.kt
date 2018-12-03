package com.adventofcode.dec2016.day14

import java.security.MessageDigest

class HashGenerator {

    private val digest: MessageDigest = MessageDigest.getInstance("MD5")


    fun simpleKey(input: String) = digest.digest(input.toByteArray())
            .joinToString("") { String.format("%02x", it) }

    fun stretchedKey(input: String): String {
        var hash = input
        for (i in 1..2017) {
            hash = simpleKey(hash)
        }
        return hash
    }
}