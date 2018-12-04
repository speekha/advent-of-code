package com.adventofcode.dec2021.day08

class DigitalDisplay {

    fun mapWires(input: String): Map<Int, String> {
        val patterns = input.split(" ")
        val outputs = ('a'..'g').associateWith { ('a'..'g').toMutableList() }.toMutableMap()
        val one = patterns.first { it.length == 2 }
        fixSegment(outputs, one.toList(), digits[1]!!.toList())
        val seven = patterns.first { it.length == 3 }
        val a = seven.toMutableList()
        a.removeAll(one.toList())
        fixSegment(outputs, a, listOf('a'))
        val four = patterns.first { it.length == 4 }
        fixSegment(outputs, four.toList(), digits[4]!!.toList())
        var countSegments = countSegmentUses(patterns, 5)
        fixSegment(outputs, countSegments[1]!!, listOf('b', 'e'))
        fixSegment(outputs, countSegments[2]!!, listOf('c', 'f'))
        fixSegment(outputs, countSegments[3]!!, listOf('a', 'd', 'g'))

        countSegments = countSegmentUses(patterns, 6)
        fixSegment(outputs, countSegments[2]!!, listOf('c', 'd', 'e'))
        fixSegment(outputs, countSegments[3]!!, listOf('a', 'b', 'f', 'g'))
        return digits.mapValues { (digit, value) ->
            value.map { outputs[it]!![0] }.sorted().joinToString("")
        }
    }

    private fun countSegmentUses(input: List<String>, size: Int) =
        input.filter { it.length == size }
            .flatMap { it.toList() }.groupBy { it }
            .mapValues { (key, value) -> value.size }
            .entries
            .groupBy { it.value }
            .mapValues { (_, value) -> value.map { it.key } }

    private fun fixSegment(
        outputs: MutableMap<Char, MutableList<Char>>,
        input: List<Char>,
        expected: List<Char>
    ) {
        outputs.forEach { (wire, options) ->
            if (wire in expected) {
                outputs[wire]?.removeAll { it !in input }
            } else {
                outputs[wire]?.removeAll { it in input }
            }
        }
    }

    fun count1478(input: List<String>) = input.map {
        it.substringAfter("| ").split(" ").count { it.length in listOf(2, 3, 4, 7) }
    }.sum()

    fun readCode(input: String): String {
        val mapping = mapWires(input.substringBefore(" | ")).entries.associate { (k, v) -> v to k }
        val digits = input.substringAfter(" | ").split(" ").map { it.toList().sorted().joinToString("") }
        return digits.map { mapping[it] }.joinToString("")
    }

    val digits = mapOf<Int, String>(
        1 to "cf",
        7 to "acf",
        4 to "bcdf",
        2 to "acdeg",
        3 to "acdfg",
        5 to "abdfg",
        6 to "abdefg",
        0 to "abcefg",
        9 to "abcdfg",
        8 to "abcdefg",
    )


}
