package com.adventofcode.dec2023.day02

import kotlin.math.max

class CubeGameEngine() {
    fun isGamePossible(input: String, red: Int, green: Int, blue: Int): Boolean =
        isGamePossible(input, Configuration(red, green, blue))

    fun isGamePossible(input: String, conf: Configuration): Boolean {
        val (game, output) = input.split(": ")
        val draws = output.split("; ")
        return draws.all { isDrawPossible(it, conf) }
    }

    fun isDrawPossible(input: String, conf: Configuration): Boolean {
        val cubes = input.split(", ").map { it.split(" ") }
        return cubes.all {
            when {
                it[1] == "red" -> it[0].toInt() <= conf.red
                it[1] == "green" -> it[0].toInt() <= conf.green
                it[1] == "blue" -> it[0].toInt() <= conf.blue
                else -> error("Invalid input: $it")
            }
        }
    }

    fun getMinimalConfiguration(input: String): Configuration {
        val (game, output) = input.split(": ")
        val draws = output.split("; ")
        return getMinimalConfiguration(draws)
    }

    fun getMinimalConfiguration(input: List<String>): Configuration {
        var result = Configuration(0, 0, 0)
        input.forEach { draw ->
            val cubes = draw.split(", ").map { it.split(" ") }
            cubes.forEach {
                result = when {
                    it[1] == "red" -> Configuration(max(result.red, it[0].toInt()), result.green, result.blue)
                    it[1] == "green" -> Configuration(result.red, max(result.green, it[0].toInt()), result.blue)
                    it[1] == "blue" -> Configuration(result.red, result.green, max(result.blue, it[0].toInt()))
                    else -> error("Invalid input: $it")
                }
            }
        }
        return result
    }

    data class Configuration(val red: Int, val green: Int, val blue: Int) {
        val score: Int
            get() = red * blue * green
    }
}
