package com.adventofcode.dec2016.day9

import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

class FileExpander(input: String) {

    val data = input.replace(Regex(" "), "")
    val matcher = Pattern.compile("\\(\\d+x\\d+\\)").matcher(data)
    var nested: Boolean = true

    fun decompress(nested: Boolean = false): String {
        this.nested = nested
        val result = StringBuilder()
        var start = 0
        var next: Int
        var end: Int
        while (matcher.find(start)) {
            next = matcher.start()
            result.append(data.substring(start until next))
            end = matcher.end()
            val (length, repeat, _) = data.substring(next + 1 until end - 1).split("x").map { it.toInt() }
            result.append(data.substring(end until end + length).repeat(repeat))
            start = end + length
        }
        result.append(data.substring(start))
        return result.toString()
    }

    fun decompressedSize(nested: Boolean = true): Long {
        this.nested = nested
        return decompressNextMatch()
    }

    private tailrec fun decompressNextMatch(start: Int = 0, subtotal: Long = 0): Long {
        return if (!matcher.find(start)) {
            subtotal + data.length - start
        } else {
            val (next, result) = expandBlock(start, matcher)
            decompressNextMatch(next, result + subtotal)
        }
    }

    private fun expandBlock(offset: Int, matcher: Matcher): Pair<Int, Long> {
        val start = matcher.start()
        val end = matcher.end()
        var result = (start - offset).toLong()
        val (length, repeat, _) = data.substring(start + 1 until end - 1).split("x").map { it.toInt() }
        result += repeat * if (nested) {
            FileExpander(data.substring(end until end + length)).decompressedSize()
        } else {
            length.toLong()
        }
        return end + length to result
    }

}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day9/input.txt").readLines().joinToString("")
    with(FileExpander(input)) {
        println("Decompressed data length: ${decompress().length}")
    }
    with(FileExpander(input)) {
        println("Decompressed data length: ${decompressedSize()}")
    }
}