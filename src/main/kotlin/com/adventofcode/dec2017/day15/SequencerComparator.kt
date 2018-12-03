package com.adventofcode.dec2017.day15

class SequencerComparator(startValueA: Long, startValueB: Long, trailA: Int = 0, trailB: Int = 0) {

    val sequencerA = GeneratorSequencer(startValueA, 16807, trailA)

    val sequencerB = GeneratorSequencer(startValueB, 48271, trailB)

    fun countDuplicates(i: Int) = (1..i).count {
        sequencerA.computeNextBinaryValue(16) == sequencerB.computeNextBinaryValue(16)
    }

}