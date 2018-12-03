package com.adventofcode.dec2017.day17

import com.adventofcode.time

fun main(args: Array<String>) = time {
    with(SpinLock(394)) {
        println("Short-circuit value: ${shortCircuit(2017)}")
        println("Next computation: ${getPostZeroEntry(50000000)}")
    }
}