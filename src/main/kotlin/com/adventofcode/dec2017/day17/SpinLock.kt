package com.adventofcode.dec2017.day17

class SpinLock(val cycle: Int) {

    lateinit var entryList: Node

    lateinit var iterator: Node

    var count: Int = 0

    fun getState(): String = entryList.toString()

    fun shortCircuit(target: Int): Int {
        count = 0
        iterate(target)
        return (iterator.next ?: entryList).value
    }

    fun getPostZeroEntry(target: Int): Int {
        count = 0
        iterate(target)
        return (entryList.next ?: entryList).value
    }

    fun iterate(target: Int) = (0..target).forEach {
        nextStep()
        count++
    }

    private fun nextStep() {
        if (count == 0) {
            initBuffer()
        } else {
            incrementBuffer()
        }
    }

    private fun incrementBuffer() {
        stepThroughBuffer()
        iterator = iterator.insert(count)
    }

    private fun initBuffer() {
        entryList = Node(count)
        iterator = entryList
    }

    private fun stepThroughBuffer() = (1..cycle).forEach {
        iterator = iterator.next ?: entryList
    }
}