package com.adventofcode.dec2021.day16

class BitsStream(
    val data: String
) {
    var index = 0

    fun readBit(): Int {
        val c = data[index / 4].toString().toInt(16)
        val bit: Int = (c shr (3 - (index % 4))) and 1
        index++
        return bit
    }

    fun readValue(len: Int): Int = (0 until len).fold(0) { acc, _ ->
        2 * acc + readBit()
    }

}