package com.adventofcode.dec2020.day25

class CryptoBreaker(
    private val subjectNumber: Long = 7L
) {

    fun generateKey(loopSize: Int, base: Long = subjectNumber): Long {
        var result = 1L
        repeat(loopSize) {
            result = multiply(result, base)
        }
        return result
    }

    fun breakKey(vararg key: Long): Pair<Long, Int> = breakKeys(hashSetOf(*key.toTypedArray()))

    private fun breakKeys(keys: HashSet<Long>): Pair<Long, Int> {
        var result = 1L
        var counter = 0
        while (result !in keys) {
            result = multiply(result)
            counter++
        }
        return result to counter
    }

    private fun multiply(result: Long, base: Long = subjectNumber) = result * base % 20201227L

    fun buildEncryptionKey(cardKey: Long, doorKey: Long): Long {
        val keys = longArrayOf(cardKey, doorKey)
        val (key, loopSize) = breakKey(*keys)
        return generateKey(loopSize, keys.first { it != key })
    }
}