package com.adventofcode.dec2020.day17

class ConwayCubes(
    input: List<String>,
    private val dimensions: Int = 3
) {

    val ranges = Array<Range>(dimensions) {
        when (it) {
            0 -> Range(0, input[0].length - 1)
            1 -> Range(0, input.size - 1)
            else -> Range(0, 0)
        }
    }

    private var cubes = input.flatMapIndexed { y, row ->
        row.mapIndexed { x, _ ->
            listOf(x, y) + (2 until dimensions).map { 0 }
        }.filter { (x, y) -> input[y][x] == '#' }
    }.associateWith { true }

    fun countActiveCubes(): Int = cubes.size

    fun iterate() {
        cubes = computeNewMap()
    }

    private fun computeNewMap(
        newMap: MutableMap<Position, Boolean> = mutableMapOf(),
        pos: List<Int> = listOf()
    ): Map<Position, Boolean> = newMap.apply {
        if (pos.size == dimensions) {
            computeCube(pos, this)
        } else {
            ranges[pos.size].extended().forEach { i ->
                computeNewMap(this, pos + i)
            }
        }
    }

    private fun computeCube(pos: List<Int>, newMap: MutableMap<Position, Boolean>) {
        val active = if (getCube(pos)) countNeighbors(pos) in 2..3 else countNeighbors(pos) == 3
        if (active) {
            newMap[pos] = true
            updateBoundaries(pos)
        }
    }

    private fun updateBoundaries(pos: List<Int>) {
        (0 until dimensions).forEach { i ->
            ranges[i].min = pos[i]
            ranges[i].max = pos[i]
        }
    }

    fun countNeighbors(position: Position, neighbor: List<Int> = listOf()): Int = if (neighbor.size == dimensions)
        position.checkNeighbor(neighbor)
    else
        (-1..1).fold(0) { acc, i ->
            if (acc >= 4) acc else acc + countNeighbors(position, neighbor + i)
        }

    private fun Position.checkNeighbor(neighbor: List<Int>): Int =
        if (neighbor.any { it != 0 } && getCube(zip(neighbor) { a, b -> a + b })) 1 else 0

    private fun getCube(position: Position): Boolean {
        if (position.size != dimensions) {
            error("Incorrect position: $position")
        }
        return cubes[position] ?: false
    }
}

typealias Position = List<Int>