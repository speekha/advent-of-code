package com.adventofcode.dec2022.day23

import com.adventofcode.positioning.Neighbor
import com.adventofcode.positioning.Position
import java.util.LinkedList

class ElfSpreader(val elves: List<Elf>) {

    val options = LinkedList<MoveOption>().apply { addAll(MoveOption.values()) }

    fun spread(): Boolean {
        val map = elves.map { it.position }.toSet()
        val proposals = elves.map {  elf ->
            if (Neighbor.values().any { elf.position + it in map }) {
                options.firstOrNull { move ->
                    move.neighbors.none { elf.position + it in map }
                }?.let { move -> elf.position + move.direction }
            } else null
        }
        val validProposals = proposals.groupBy { it }
            .mapValues { (_, list) -> list.count() }
            .filter { (_, value) -> value == 1 }.keys
        proposals.forEachIndexed { index, position ->
            if (position != null && position in validProposals) {
                elves[index].position = position
            }
        }
        val first = options.poll()
        options.add(first)
        return proposals.any { it != null }
    }

    fun countEmptyTiles(): Int {
        var xMin = 0
        var yMin = 0
        var xMax = 0
        var yMax = 0
        elves.forEach { elf ->
            if (elf.position.x < xMin) {
                xMin = elf.position.x
            }
            if (elf.position.x > xMax) {
                xMax = elf.position.x
            }
            if (elf.position.y < yMin) {
                yMin = elf.position.y
            }
            if (elf.position.y > yMax) {
                yMax = elf.position.y
            }
        }
        println("$xMin - $xMax / $yMin - $yMax")
        return (xMax - xMin + 1) * (yMax - yMin + 1) - elves.size
    }


    companion object {

        fun fromInput(input: List<String>) = ElfSpreader(input.withIndex()
            .filter { (_, row) -> '#' in row }
            .flatMap { (y, row) ->
                row.withIndex().filter { (index, c) -> c == '#' }.map { Elf(Position(it.index, y)) }
            }
        )
    }
}