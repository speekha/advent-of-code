package com.adventofcode.dec2022.day03

class Rucksack(
    val pocket1: String,
    val pocket2: String
) {
    constructor(input: String) : this(input.take(input.length / 2), input.drop(input.length / 2))

    fun findMistake(): Char = pocket1.toSet().intersect(pocket2.toList().toSet()).first()
}