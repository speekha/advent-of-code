package com.adventofcode.dec2016.day11

import java.io.File
import java.util.*

data class RTGFacility(
        val floors: List<MutableList<Item>>,
        val elevator: Int = 0
) {

    val hash: String by lazy { computeHash() }

    constructor(input: List<String>) : this(input.map { parseFloor(it) })

    fun move(move: Move): RTGFacility = RTGFacility(floors.map { it.map { it }.toMutableList() }, elevator + move.direction).also {
        it.floors[elevator].removeAll(move.payload)
        it.floors[it.elevator].addAll(move.payload)
    }

    fun getPossibleMoves(): List<Move> = arrayOf(-1, 1).flatMap { d ->
        val singles = floors[elevator].map { Move(d, setOf(it)) }
        val double = floors[elevator].indices.flatMap { i ->
            (i + 1..floors[elevator].indices.last).map { j ->
                Move(d, setOf(floors[elevator][i], floors[elevator][j]))
            }
        }
        singles + double
    }.filter { isValidMove(it) }

    fun isValidMove(move: Move): Boolean = when {
        elevator + move.direction !in floors.indices -> false
        else -> {
            areChipsAtRisk(move.payload.toList())
                    && areChipsAtRisk(floors[elevator + move.direction] + move.payload)
                    && areChipsAtRisk(floors[elevator] - move.payload)
        }
    }

    private fun areChipsAtRisk(dest: List<Item>): Boolean {
        val group = dest
                .groupBy { it.type }
        val chips = group[ItemType.MICROCHIP] ?: emptyList()
        val generators = group[ItemType.GENERATOR] ?: emptyList()
        return generators.isEmpty() || chips.all { chip ->
            generators.any { it.isotope == chip.isotope }
        }
    }

    fun computeHash(): String {
        return elevator.toString() + "/" + floors.map {
            it.sortedBy { it.toString() }.joinToString("")
        }.joinToString("/")
    }

    companion object {
        fun parseFloor(floor: String): MutableList<Item> {
            val result = mutableListOf<Item>()
            val tokens = floor.replace(Regex("[.,]"), "").split(" ")
            result += tokens.indices
                    .filter { tokens[it] == "generator" || tokens[it] == "microchip" }
                    .map {
                        Item(
                                tokens[it - 1].split("-")[0],
                                ItemType.valueOf(tokens[it].toUpperCase())
                        )
                    }
            return result
        }

        fun moveEveryThing(init: RTGFacility): Int {
            var count = 0
            var found = false
            val steps = mutableMapOf<String, Step>()
            val queue = LinkedList<Step>()
            queue.push(Step(init, 0))
            while (!found) {
                with(queue.pop()) {
                    if (!steps.containsKey(facility.hash)) {
                        count = numberOfSteps
                        steps[facility.hash] = this
                        found = facility.floors.indices.all { it == 3 || facility.floors[it].isEmpty() }
                        queue.addAll(
                                facility.getPossibleMoves()
                                        .map { Step(facility.move(it), numberOfSteps + 1) }
                        )
                    }
                }
            }
            return count
        }
    }

    data class Step(
            val facility: RTGFacility,
            val numberOfSteps: Int
    )

}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day11/input.txt").readLines()
    val facility = RTGFacility(input)
    println("Optimal path: ${RTGFacility.moveEveryThing(facility)}")
    facility.floors[0] += RTGFacility.parseFloor(
            "An elerium generator. " +
                    "An elerium-compatible microchip. " +
                    "A dilithium generator. " +
                    "A dilithium-compatible microchip.")
    println("Optimal path: ${RTGFacility.moveEveryThing(facility)}")
}