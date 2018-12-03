package com.adventofcode.dec2016.day7

import java.io.File
import java.util.regex.Pattern

class IPv7Analyzer {

    fun countIPv7WithTLS(input: List<String>) = input.count { hasTlsSupport(it) }

    fun hasTlsSupport(input: String) = extractSupernetParts(input).any { hasABBAnnotation(it) }
            && extractHypernetParts(input).none { hasABBAnnotation(it) }

    fun countIPv7WithSSL(input: List<String>) = input.count { hasSslSupport(it) }

    fun hasSslSupport(input: String): Boolean {
        val abAccessors = extractSupernetParts(input).flatMap { extractABA(it) }
        return abAccessors.isNotEmpty() && extractHypernetParts(input).any { abAccessors.any { aba -> it.contains(getBabFrom(aba)) } }
    }

    fun extractSupernetParts(input: String): List<String> = input.split(Regex("\\[[^\\[]*\\]"))

    fun extractHypernetParts(input: String): List<String> {
        val pattern = Pattern.compile("\\[[^\\[]*\\]")
        val matcher = pattern.matcher(input)
        val result = mutableListOf<String>()
        while (matcher.find()) {
            result += matcher.group().replace(Regex("[\\[\\]]"), "")
        }
        return result
    }

    fun hasABBAnnotation(input: String): Boolean = (0..(input.length - 4)).any { isValidABBA(input.substring(it, it + 4)) }

    fun extractABA(input: String) = (0..(input.length - 3)).map { input.substring(it, it + 3) }.filter { isValidABA(it) }

    private fun getBabFrom(aba: String): String = "${aba[1]}${aba[0]}${aba[1]}"

    private fun isValidABBA(input: String) = input[0] != input[1] && input[0] == input[3] && input[1] == input[2]

    private fun isValidABA(input: String) = input[0] != input[1] && input[0] == input[2]

}

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day7/input.txt").readLines()
    with(IPv7Analyzer()) {
        println("TLS addresses: ${countIPv7WithTLS(input)}")
        println("SSL addresses: ${countIPv7WithSSL(input)}")
    }
}
