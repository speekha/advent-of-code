package com.adventofcode.dec2017.day13

import com.adventofcode.time
import java.io.File
import kotlin.system.measureTimeMillis

class PacketSmuggler(input: List<String>) {

    private val layers = analyseFirewall(input)

    companion object {
        fun analyseFirewall(input: List<String>) = input.asSequence().associate {
            val (depth, range) = it.split(": ")
            depth.toInt() to range.toInt()
        }
    }

    fun getRange(depth: Int): Int = layers.getOrDefault(depth, 0)

    fun isScannerAtTop(layer: Int, time: Int) = layers[layer]?.let { range -> time % (2 * (range - 1)) == 0 } ?: false

    fun isPathFree(delay: Int = 0): Boolean = layers.keys.none { isScannerAtTop(it, it + delay) }

    fun traverse(delay: Int = 0): List<Int> = layers.keys.filter { isScannerAtTop(it, it + delay) }

    fun computeSeverity(delay: Int = 0) = traverse(delay).sumBy { it * getRange(it) }

    fun avoidScanners(): Int {
        var i = 0
        while (!isPathFree(i)) {
            i++
        }
        return i
    }
}