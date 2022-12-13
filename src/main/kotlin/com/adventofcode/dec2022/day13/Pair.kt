package com.adventofcode.dec2022.day13

import java.util.LinkedList

class Pair(input: List<String>) {

    val left: ListElement = parse(input[0])

    val right: ListElement = parse(input[1])

    private fun parse(input: String): ListElement = PairParser(input).parse()

    fun isOrdered(): Boolean = left < right

}