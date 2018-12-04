package com.adventofcode.dec2015.day04

import com.adventofcode.md5

class AdventCoinMiner {
    fun findHash(input: String, length: Int): Int {
        var i = 0
        var hash = ""
        val prefix = "0".repeat(length)
        while (!hash.startsWith(prefix)) {
            i++
            hash = md5("$input$i")
        }
        return i
    }
}