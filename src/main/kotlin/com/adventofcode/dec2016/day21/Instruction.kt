package com.adventofcode.dec2016.day21

import kotlin.math.max
import kotlin.math.min

sealed class Instruction {
    abstract fun process(input: String): String

    open fun reverse(): Instruction = this
}

class SwapPosition(x: Int, y: Int) : Instruction() {

    private val first = min(x, y)

    private val second = max(x, y)

    override fun process(input: String): String = input.substring(0 until first) +
            input[second] +
            input.substring(first + 1 until second) +
            input[first] +
            input.substring(second + 1)
}

class SwapLetters(val letterA: Char, val letterB: Char) : Instruction() {

    override fun process(input: String): String = input.map {
        when (it) {
            letterA -> letterB
            letterB -> letterA
            else -> it
        }
    }.joinToString("")
}

class Reverse(x: Int, y: Int) : Instruction() {

    private val start = min(x, y)

    private val end = max(x, y)

    override fun process(input: String): String = input.substring(0 until start) +
            input.substring(start..end).reversed() +
            input.substring(end + 1)
}

class Rotate(val offset: Int, val direction: Int) : Instruction() {
    override fun process(input: String): String = if (direction == LEFT) {
        input.rotateLeft(offset)
    } else {
        input.rotateLeft( input.length - offset)
    }

    override fun reverse(): Instruction {
        return Rotate(offset, -direction)
    }
}


class RotateForLetter(val letter: Char) : Instruction() {
    override fun process(input: String): String {
        val offset = input.indexOf(letter)
        val finalOffset = (offset + 1 + if (offset >= 4) 1 else 0) % input.length
        return input.rotateLeft(input.length - finalOffset)
    }

    override fun reverse(): Instruction {
        return ReverseRotation(this)
    }
}

class ReverseRotation(val rotator: RotateForLetter) : Instruction() {
    override fun process(input: String): String {
        return (1..input.length)
                .map { input.rotateLeft(it) }
                .first { rotator.process(it) == input }
    }

    override fun reverse(): Instruction = rotator
}

class Move(val x: Int, val y: Int) : Instruction() {

    override fun process(input: String): String = input
            .toMutableList().apply {
                removeAt(x)
                add(y, input[x])
            }.joinToString("")

    override fun reverse(): Instruction {
        return Move(y, x)
    }
}

object Noop : Instruction() {
    override fun process(input: String): String = input
}

private fun String.rotateLeft(offset: Int) = substring(offset) + substring(0 until offset)

const val LEFT = 1

const val RIGHT = -1