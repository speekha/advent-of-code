package com.adventofcode.dec2019.day14

data class Chemical(
        val element: String,
        val quantity: Int
) {
    companion object {
        operator fun invoke(input: String): Chemical {
            val (qty, element) = input.split(" ")
            return Chemical(element, qty.toInt())
        }
    }
}
