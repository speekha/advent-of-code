package com.adventofcode.dec2017.day21

data class Pattern(
        val input: String,
        val output: Array<BooleanArray>
) {

    val size = input.indexOf('/')

    fun match(array: Array<BooleanArray>) = array.joinToString("/") {
        it.joinToString("") { if (it) "#" else "." }
    } == input
}