package com.adventofcode.dec2023.day05

class ResourceMapper() {
    private val mapping = mutableListOf<Pair<LongRange, Long>>()
    private val reverse = mutableListOf<Pair<LongRange, Long>>()

    fun addRange(dest: Long, src: Long, window: Long) {
        val source = src..src + window
        val offset = dest - src
        mapping += source to offset
        reverse += (dest..dest + window) to -offset
    }

    fun map(input: Long): Long {
        val mappedRange = mapping.firstOrNull { input in it.first }
        return mappedRange?.let { input + it.second } ?: input
    }

    fun revert(input: Long): Long {
        val mappedRange = reverse.firstOrNull { input in it.first }
        return mappedRange?.let { input + it.second } ?: input
    }
}