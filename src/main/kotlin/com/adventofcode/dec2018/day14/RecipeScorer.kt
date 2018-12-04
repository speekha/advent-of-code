package com.adventofcode.dec2018.day14

import java.util.*

class RecipeScorer {

    val recipes: MutableList<Int> = mutableListOf(3, 7)

    var elf0 = 0

    var elf1 = 1

    val matcher = LinkedList<Int>().also { it += recipes }

    fun iterate(iterations: Int = 1) {
        repeat(iterations) {
            val sum = recipes[elf0] + recipes[elf1]
            val split = splitRecipes(sum.toString())
            recipes += split
            matcher += split
            elf0 = (elf0 + 1 + recipes[elf0]) % recipes.size
            elf1 = (elf1 + 1 + recipes[elf1]) % recipes.size
        }
    }

    private fun splitRecipes(input: String): List<Int> {
        return input.map { it - '0' }
    }

    fun computeScore(iterations: Int): String {
        while (recipes.size < 10 + iterations) {
            iterate()
        }
        return recipes.subList(iterations, iterations + 10).joinToString("")
    }

    tailrec fun countRecipes(input: String, discarded: Int = 0): Int {
        var i = discarded
        iterate()
        if (matcher.size > input.length) {
            if (matcher.joinToString("").indexOf(input) >= 0) {
                return i + matcher.joinToString("").indexOf(input)
            } else {
                while (matcher.isNotEmpty() &&
                        (matcher.size > input.length || matcher[0] != (input[0] - '0'))) {
                    matcher.poll()
                    i++
                }
            }
        }
        return countRecipes(input, i)
    }


}

fun main() {
    val input = "047801"
    var recipeTester = RecipeScorer()
    println("Score: ${recipeTester.computeScore(input.toInt())}")
    recipeTester = RecipeScorer()
    println("Score: ${recipeTester.countRecipes(input)}")
}