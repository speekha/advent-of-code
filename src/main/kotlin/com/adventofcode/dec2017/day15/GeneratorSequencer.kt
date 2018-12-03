package com.adventofcode.dec2017.day15

class GeneratorSequencer(
        startValue: Long,
        val factor: Long,
        trailingZeros: Int = 0
) {

    var currentValue = startValue

    val tail = "0".repeat(trailingZeros)

    fun computeNextBinaryValue(len: Int = 32): String {
        var binaryString: String

        do {
            binaryString = Integer.toBinaryString(computeNextValue().toInt())
        } while (!binaryString.endsWith(tail))

        return binaryString.padStart(32, '0').takeLast(len)
    }

    fun computeNextValue(): Long = (currentValue * factor % 2147483647).apply { currentValue = this }

}