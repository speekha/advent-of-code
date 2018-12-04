package com.adventofcode.dec2020.day14

class MaskedProgramProcessor {

    private val memory: MutableMap<Long, Long> = mutableMapOf()

    private var mask: String = "X".repeat(36)
        set(value) {
            field = value
            andMask = value.replace('X', '1').toLong(2)
            orMask = value.replace('X', '0').toLong(2)
        }

    private var andMask: Long = 0xFFFFFFFFFF

    private var orMask: Long = 0x0

    fun process(input: List<String>, version: Int = 1) {
        input.forEach {
            when {
                it.startsWith("mask = ") -> mask = it.drop(7)
                version == 2 -> saveV2(it)
                else -> save(it)
            }
        }
    }

    private fun save(input: String) {
        val index = input.substring(4, input.indexOf(']')).toLong()
        val value = input.drop(input.indexOf(" = ") + 3).toLong()
        memory[index] = value and andMask or orMask
    }

    private fun saveV2(input: String) {
        val value = input.drop(input.indexOf(" = ") + 3).toLong()
        val index = input.substring(4, input.indexOf(']')).toLong() or andMask
        val x = mask.mapIndexedNotNull { i, c -> i.takeIf { c == 'X' } }
        saveWithMask(value, index, x)
    }

    private fun saveWithMask(value: Long, index: Long, x: List<Int>) {
        val mask = 1L shl (35 - x[0])
        val remaining = x.drop(1)
        val indexWithOne = index or mask
        val indexWithZero = index and mask.inv()
        if (remaining.isEmpty()) {
            memory[indexWithOne] = value
            memory[indexWithZero] = value
        } else {
            saveWithMask(value, indexWithOne, remaining)
            saveWithMask(value, indexWithZero, remaining)
        }
    }

    operator fun get(index: Int): Long = memory[index.toLong()] ?: 0

    fun sumMemory(): Long = memory.values.sum()
}