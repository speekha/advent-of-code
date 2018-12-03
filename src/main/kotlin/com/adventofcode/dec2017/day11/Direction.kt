package com.adventofcode.dec2017.day11

enum class Direction {
    n, ne, se, s, sw, nw;

    fun opposite() = when (this) {
        n -> s
        ne -> sw
        se -> nw
        s -> n
        sw -> ne
        nw -> se
    }

    fun rotate(degree: Int) = values()[(this.ordinal + degree) % values().size]
}