package com.adventofcode.dec2022.day01

class CalorieCounter {
    fun parseCalories(input: String): List<Int> = input
        .split("\n\n")
        .map { elf ->
            elf.split("\n").sumOf {
                it.toInt()
            }
        }

    fun findElfWithMostCalories(input: String): Int {
        return parseCalories(input).maxOf { it }
    }

    fun findElvesWithMostCalories(input: String): Int {
        return parseCalories(input).sortedDescending().take(3).sum()
    }
}