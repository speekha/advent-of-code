package com.adventofcode.dec2022.day18

import com.adventofcode.dec2017.day20.Coordinates
import com.adventofcode.processQueue

class DropletAnalyzer {
    fun countFacets(input: List<String>, allFacets: Boolean = true): Int {
        val cubes = input.map { Coordinates.parse(it) }.toSet()
        val (exposedSides, exposedFacets) = countExposedFacets(cubes)
        return if (allFacets) exposedSides else exposedSides - countInsideFacets(cubes, exposedFacets)
    }

    private fun countExposedFacets(
        cubes: Set<Coordinates>,
    ): Pair<Int, MutableSet<Coordinates>> {
        var exposedSides = 0
        val exposedFacets = mutableSetOf<Coordinates>()
        cubes.forEach { cube ->
            cube.filterNeighbors { it !in cubes }.forEach {
                exposedSides++
                exposedFacets += it
            }
        }
        return exposedSides to exposedFacets
    }

    private fun countInsideFacets(cubes: Set<Coordinates>, exposedFacets: Set<Coordinates>): Int {
        val outside = outsideLayer(exposedFacets, cubes).toMutableSet()
        val remaining = exposedFacets.filter { it !in outside }.toMutableSet()
        processQueue<Coordinates>(outside) { current ->
            current.filterNeighbors { it !in outside && it in exposedFacets }.onEach {
                outside += it
                remaining -= it
            }
        }

        return remaining.sumOf { current -> current.filterNeighbors { it in cubes }.size }
    }

    private fun outsideLayer(exposedFacets: Set<Coordinates>, cubes: Set<Coordinates>) = exposedFacets.filter {
        it.checkAxis(cubes, Coordinates::y, Coordinates::z, Coordinates::x)
                || it.checkAxis(cubes, Coordinates::x, Coordinates::z, Coordinates::y)
                || it.checkAxis(cubes, Coordinates::x, Coordinates::y, Coordinates::z)
    }

    private fun Coordinates.checkAxis(
        cubes: Set<Coordinates>,
        a: Coordinates.() -> Int,
        b: Coordinates.() -> Int,
        c: Coordinates.() -> Int,
    ): Boolean {
        val alongAxis = cubes.filter { it.a() == a() && it.b() == b() }.partition { it.c() > c() }
        return (alongAxis.first.isEmpty() || alongAxis.second.isEmpty())
    }

    private fun Coordinates.filterNeighbors(filter: (Coordinates) -> Boolean): List<Coordinates> =
        Sides.values().map { this + it.direction }.filter(filter)
}