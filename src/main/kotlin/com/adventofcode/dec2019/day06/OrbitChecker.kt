package com.adventofcode.dec2019.day06

import java.util.*

class OrbitChecker(input: List<String>) {

    val orbits: Map<String, OrbitalObject> = mapOrbits(input)

    val orbitalTreeRoot
        get() = orbits["COM"] ?: error("Corrupted map")

    private fun mapOrbits(input: List<String>): Map<String, OrbitalObject> = mutableMapOf<String, OrbitalObject>().apply {
        input.forEach {
            val (parentId, childId) = it.split(")")
            val parent = getOrPut(parentId) { OrbitalObject(parentId) }
            val child = getOrPut(childId) { OrbitalObject(childId) }
            parent.children += child
            child.parent = parent
        }
    }

    fun countOrbits(): Int = orbitalTreeRoot.countOrbits()

    fun countOrbitalTransfers(from: String, to: String): Int {
        val queue = LinkedList<Pair<OrbitalObject, Int>>()
        val processed = mutableMapOf<String, Boolean>()
        fun processPlanet(planet: OrbitalObject, distance: Int) {
            if (processed[planet.name] != true) {
                queue.add(planet to distance + 1)
                processed[planet.name] = true
            }
        }

        val start = orbits[from] ?: error("Starting point not found")
        queue.add(start to 0)
        processed[from] = true
        while (queue.isNotEmpty()) {
            val (planet, distance) = queue.pop()
            if (planet.name == to) {
                return distance - 2
            } else {
                planet.parent?.let { parent ->
                    processPlanet(parent, distance)
                }
                planet.children.forEach { child ->
                    processPlanet(child, distance)
                }
            }
        }
        error("Route not found")
    }
}
