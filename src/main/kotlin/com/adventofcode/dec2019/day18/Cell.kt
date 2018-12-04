package com.adventofcode.dec2019.day18

sealed class Cell {
    object Wall : Cell()
    data class Door(val name: Char) : Cell()
    data class Key(val name: Char) : Cell()
    object Entrance : Cell()
    object Empty : Cell()

    override fun toString(): String = this.javaClass.simpleName
}