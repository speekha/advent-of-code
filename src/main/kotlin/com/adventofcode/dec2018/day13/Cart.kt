package com.adventofcode.dec2018.day13

data class Cart(
        val id: Int,
        var position: Position,
        var direction: Direction,
        var nextTurn: Turn = Turn.LEFT
)