package com.adventofcode.dec2017.day25

class MemorySlot(
        var value: Int = 0,
        var left: MemorySlot? = null,
        var right: MemorySlot? = null
) {
    fun getOrAddLeft(): MemorySlot = left ?: MemorySlot().also {
        left = it
        it.right = this
    }

    fun getOrAddRight(): MemorySlot = right ?: MemorySlot().also {
        right = it
        it.left = this
    }
}