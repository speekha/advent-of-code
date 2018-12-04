package com.adventofcode.dec2016.day19

class WhiteElephantGame(val elfCount: Int) {

    fun computeWinner(chooseNeighbor: Boolean = true): Int {
        val elf = initElfCircle()
        var size = elfCount
        var current = if (chooseNeighbor) elf else (1 until elfCount / 2).fold(elf) { it, _ -> it.next }
        while (current != current.next) {
            current.next = current.next.next
            size--
            if (chooseNeighbor || size % 2 == 0) {
                current = current.next
            }
        }
        return current.index
    }

    private fun initElfCircle(): Elf = Elf(1).also {
        var current = it
        for (i in 2..elfCount) {
            val newElf = Elf(i)
            current.next = newElf
            current = newElf
        }
        current.next = it
    }

}

fun main() {
    println("Winner is: ${WhiteElephantGame(3017957).computeWinner()}")
    println("Second Winner is: ${WhiteElephantGame(3017957).computeWinner(false)}")
}
