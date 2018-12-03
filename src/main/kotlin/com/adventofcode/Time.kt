package com.adventofcode

import kotlin.system.measureTimeMillis

fun time(block: () -> Unit) {
    println("Execution time: ${measureTimeMillis(block)}ms")
}