package com.adventofcode.dec2022.day06

class ComDevice {
    fun findPacketMarker(input: String): Int = findMarker(input, 4)

    fun findMessageMarker(input: String): Int = findMarker(input, 14)

    private fun findMarker(input: String, size: Int) = input
        .windowed(size)
        .withIndex()
        .first { (_, chunk) ->
            chunk.toSet().size == size
        }.index + size

}
