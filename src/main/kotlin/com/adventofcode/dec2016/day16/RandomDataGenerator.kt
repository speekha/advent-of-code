package com.adventofcode.dec2016.day16

class RandomDataGenerator(
        seed: String
) {

    private var state = seed

    fun iterate(data: String): String = "${data}0${invert(data)}"

    private fun invert(seed: String): String = seed
            .reversed()
            .map {
                if (it == '0') "1" else "0"
            }
            .joinToString("")

    fun checksumStep(data: String): String = (0 until data.length / 2).joinToString("") {
        if (data[2 * it] == data[2 * it + 1]) "1" else "0"
    }

    tailrec fun checksum(data: String, max: Int = data.length): String {
        val trim = data.substring(0 until max)
        return if (max % 2 == 1) trim else checksum(checksumStep(trim), max / 2)
    }

    fun fillDrive(space: Int): String {
        state = generateSequence(state) { iterate(it) }.first { it.length >= space }
        return checksum(state, space)
    }
}

fun main(args: Array<String>) {
    val generator = RandomDataGenerator("00101000101111010")
    println("Checksum = " + generator.fillDrive(272))
    println("Checksum = " + generator.fillDrive(35651584))
}