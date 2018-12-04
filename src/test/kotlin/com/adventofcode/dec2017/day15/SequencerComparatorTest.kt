package com.adventofcode.dec2017.day15

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SequencerComparatorTest {


    @Test
    fun `should have 1 duplicate`() {
        val judge = SequencerComparator(65, 8921)
        Assertions.assertEquals(1, judge.countDuplicates(5))
    }

    @Test
    fun `should have 588 duplicate`() {
        val judge = SequencerComparator(65, 8921)
        Assertions.assertEquals(588, judge.countDuplicates(40000000))
    }

    @Test
    fun `first duplicate after 1056 rounds`() {
        val judge = SequencerComparator(65, 8921, 2, 3)
        Assertions.assertEquals(0, judge.countDuplicates(1055))
        Assertions.assertEquals(1, judge.countDuplicates(1056))
    }

    @Test
    fun `should have 309 duplicate`() {
        val judge = SequencerComparator(65, 8921, 2, 3)
        Assertions.assertEquals(309, judge.countDuplicates(5000000))
    }
}