package com.adventofcode.dec2017.day15

fun main() {
    var judge = SequencerComparator(783, 325)
    println("Duplicates (1) : ${judge.countDuplicates(40000000)}")
    judge = SequencerComparator(783, 325, 2, 3)
    println("Duplicates (2) : ${judge.countDuplicates(5000000)}")
}