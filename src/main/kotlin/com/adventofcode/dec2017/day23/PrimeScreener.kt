package com.adventofcode.dec2017.day23

import kotlin.math.sqrt

class PrimeScreener(var a: Int = 0,
                    var h: Int = 0) {
    fun execute(): Int {
        var b = 57
        var c = 57
        if (a != 0) {
            b = 105700
            c = 122700
        }
        loop@ for (i in b..c step 17) {
            for (d in 2 until sqrt(i.toFloat()).toInt()) {
                if (i % d == 0) {
                    h++
                    continue@loop
                }
            }
        }
        return h
    }
}

fun main(args: Array<String>) {
    with(PrimeScreener(a = 1)) {
        println("h = ${execute()}")
    }
}