package com.adventofcode.dec2018.day15

sealed class Fighter{

    abstract var position: Position
    abstract val enemy: Char
    data class Elf(override var position: Position) : Fighter() {
        override val enemy: Char = 'G'
    }
    data class Goblin(override var position: Position) : Fighter(){
        override val enemy: Char = 'E'
    }
}