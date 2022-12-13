package com.adventofcode.dec2022.day13

import java.util.LinkedList

class PairParser(val input: String) {

    private val pile = LinkedList<ListElement>()

    private var currentInt: Int? = null

    fun parse(): ListElement {
        input.forEach {
            processCharacter(it)
        }
        return pile.pop()
    }

    private fun processCharacter(c: Char) {
        when (c) {
            '[' -> pile.push(ListElement())
            in '0'..'9' -> currentInt = (currentInt ?: 0) * 10 + (c - '0')
            ',', ']' -> nextElement()
        }
    }

    private fun nextElement() {
        val child = currentInt?.let {
            IntElement(currentInt!!)
        } ?: pile.pop()
        if (pile.isNotEmpty()) {
            pile.peekFirst().value += child
        } else {
            pile.push(child as ListElement)
        }
        currentInt = null
    }
}
