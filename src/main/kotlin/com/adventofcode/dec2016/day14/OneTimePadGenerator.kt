package com.adventofcode.dec2016.day14

import com.adventofcode.time

class OneTimePadGenerator(val salt: String, val hashFunction: (String) -> String) {

    private val cache = mutableMapOf<Int, String>()

    fun generatePads(count: Int): Int {
        var validHashes = mutableListOf<Int>()
        var preValidHashes = mutableListOf<Int>()
        while (validHashes.size < count) {

        }

        return 0
    }

    tailrec fun generate(count: Int, start: Int = 0): Int {
        var neededKeys = count
        if (isHashValid(start)) {
            println("$start ${getMD5(start)} $neededKeys")
            neededKeys--
            if (neededKeys == 0) {
                return start
            }
        }
        cache.remove(start)
        return generate(neededKeys, start + 1)
    }

    fun isHashValid(index: Int) = getMD5(index).let { hash ->
        (0..hash.length - 3).any {
            checkForCharacter(hash, it, index)
        }
    }

    fun getMD5(index: Int): String = cache[index] ?: hashFunction(getSalt(index)).also { cache[index] = it }

    private fun getSalt(index: Int) = "$salt$index"

    fun checkForCharacter(hash: String, i: Int, index: Int) = hash[i].let { char ->
        char == hash[i + 1] && char == hash[i + 2] &&
                checkNext1000Hashes(char.toString().repeat(5), index)
    }

    private fun checkNext1000Hashes(needle: String, index: Int): Boolean {
        return (index + 1..index + 1000).any {
            getMD5(it).let { hash ->
                if (hash.contains(needle)) {
                    print("$needle $hash $it ${it - index} ")
                    true
                } else {
                    false
                }
            }
        }
    }

}

fun main() = time {
    val input = "cuanljph"
    val generator = OneTimePadGenerator(input, HashGenerator()::simpleKey)
    println("64th key for: ${generator.generate(70)}")
}