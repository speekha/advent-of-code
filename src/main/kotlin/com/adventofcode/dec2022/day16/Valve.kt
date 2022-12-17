package com.adventofcode.dec2022.day16

data class Valve(
    val name: String,
    val flow: Int,
    val next : List<String>
) {
    constructor(input: String) : this(
        input.substring(6, 8),
        input.substring(23, input.indexOf(";")).toInt(),
        input.drop(input.indexOf("valve") + 6).trim().split(", ")
    )
}
