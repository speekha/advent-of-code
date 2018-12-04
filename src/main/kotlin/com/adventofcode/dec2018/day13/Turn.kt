package com.adventofcode.dec2018.day13

enum class Turn(val inc: Int) {
    LEFT(-1), STRAIGHT(0), RIGHT(1);

    operator fun inc(): Turn = values()[(ordinal + 1) % values().size]
}
