package com.adventofcode

import java.io.File
import java.security.MessageDigest
import java.util.*
import kotlin.system.measureTimeMillis

fun time(block: () -> Unit) {
    println("Execution time: ${measureTimeMillis(block)}ms")
}

fun debug(msg: Any) {
//        println(msg)
}

fun md5(message: String): String = MessageDigest.getInstance("MD5")
    .digest(message.toByteArray())
    .joinToString("") { String.format("%02x", it) }

fun Any.readInputAsList(fileName: String = "input.txt"): List<String> = getInputFile(fileName).readLines()

fun Any.readInput(fileName: String = "input.txt"): String = getInputFile(fileName).readText().replace("\r", "")

private fun Any.getInputFile(fileName: String): File {
    val packageName = this::class.java.`package`.name.replace('.', '/')
    return File("src/main/kotlin/$packageName/$fileName")
        .takeIf { it.exists() }
        ?: File("src/test/kotlin/$packageName/$fileName")
}

val Any.actualInput: String
    get() = readInput()

val Any.actualInputList: List<String>
    get() = readInputAsList()

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun lcm(vararg numbers: Long) = numbers.reduce { acc, number ->
    lcm(acc, number)
}

inline operator fun <reified T> T.plus(i: Int): T where T : Enum<T> =
    enumValues<T>()[(ordinal + i) % enumValues<T>().count()]

inline operator fun <reified T> T.inc(): T where T : Enum<T> = this + 1

fun <T : Any> processQueue(initial: Iterable<T>, computeNext: (T) -> List<T>) {
    val queue = LinkedList<T>()
    queue.addAll(initial)
    while (queue.isNotEmpty()) {
        val current = queue.pollFirst()
        queue.addAll(computeNext(current))
    }

}