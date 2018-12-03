package com.adventofcode.dec2017.day6


class MemoryManager {

    fun redistributeMemoryCompletely(input: IntArray): Int {
        val memorySignatures = mutableSetOf(getSignature(input))
        var cycles = 0
        var memory = input.clone()
        do {
            memory = redistributeMemory(memory)
            memorySignatures += getSignature(memory)
            cycles++
        } while (cycles == memorySignatures.size - 1)
        return cycles
    }

    tailrec fun findRedistributionCycle(mem: IntArray, signatures: Map<String, Int> = emptyMap()): Int {
        val signature = getSignature(mem)
        return if (signatures.containsKey(signature)) {
            signatures.size - signatures.getOrDefault(signature, 0)
        } else {
            val memory = redistributeMemory(mem)
            findRedistributionCycle(memory, signatures + (signature to signatures.size))
        }
    }

    fun redistributeMemory(input: IntArray): IntArray {
        val max = input.max() ?: 0
        val memBank = input.indexOf(max)
        val memory = IntArray(input.size) { if (it == memBank) 0 else input[it] }
        return memory.indices.map {
            memory[it] + (max / memory.size) + reallocationRemainder(it, memory.size, memBank to max)
        }.toIntArray()
    }

    fun reallocationRemainder(index: Int, memSize: Int, reallocatedBlocks: Pair<Int, Int>): Int {
        var distance = (index + memSize - reallocatedBlocks.first) % memSize
        distance = if (distance == 0) memSize else distance
        return if (distance <= reallocatedBlocks.second % memSize) 1 else 0
    }

    private fun getSignature(memory: IntArray) = memory.joinToString(" ")
}


fun main(args: Array<String>) {
    val input = "14 0 15 12 11 11 3 5 1 6 8 4 9 1 8 4".split(" ").map { it.toInt() }.toIntArray()
    with(MemoryManager()) {
        println("Infinite loop after ${redistributeMemoryCompletely(input)} cycles")
        println("Cycle periodicity: ${findRedistributionCycle(input)}")
    }
}
