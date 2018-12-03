package com.adventofcode.dec2016.day21

import java.io.File

class PasswordScrambler(val instructions: List<Instruction>) {

    fun scramble(input: String): String = instructions.fold(input) { input, instruction -> instruction.process(input) }

    fun descramble(input: String): String =
            instructions.reversed()
                    .map { it.reverse() }
                    .fold(input) { input, instruction -> instruction.process(input) }

    companion object {
        fun parse(instructions : List<String>): PasswordScrambler {
            return PasswordScrambler(instructions.map { parseInstruction(it) })
        }

        private fun parseInstruction(line: String): Instruction {
            val tokens = line.split(" ")
            return when(tokens[0]) {
                "swap" -> if (tokens[1] == "position")
                    SwapPosition(tokens[2].toInt(), tokens[5].toInt())
                else
                    SwapLetters(tokens[2][0], tokens[5][0])
                "reverse" -> Reverse(tokens[2].toInt(), tokens[4].toInt())
                "rotate" -> when(tokens[1]) {
                    "left" -> Rotate(tokens[2].toInt(), LEFT)
                    "right" -> Rotate(tokens[2].toInt(), RIGHT)
                    else -> RotateForLetter(tokens[6][0])
                }
                "move" -> Move(tokens[2].toInt(), tokens[5].toInt())
                else -> Noop
            }
        }
    }
}

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day21/input.txt").readLines()
    val scrambler = PasswordScrambler.parse(input)
    val scrambled = scrambler.scramble("abcdefgh")
    println("Scrambled password: $scrambled")
    val descrambled = scrambler.descramble("fbgdceah")
    println("Descrambled password: $descrambled")
}
