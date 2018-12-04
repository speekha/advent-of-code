package com.adventofcode.dec2021.day12

import java.util.*

class CaveMapper(input: List<String>) {

    val nodes: Map<String, Set<String>>

    init {
        val map = mutableMapOf<String, MutableSet<String>>()
        input.forEach {
            val (a, b) = it.split("-")
            map.getOrPut(a) { mutableSetOf(b) } += b
            map.getOrPut(b) { mutableSetOf(a) } += a
        }
        nodes = map
    }

    fun countPaths(extended: Boolean = false, path: List<String> = listOf("start")): Int = if (path.last() == "end") {
        1
    } else {
        val neighbors = nodes[path.last()]!!.filter { isAvailable(it, path, extended) }
        neighbors.sumOf { countPaths(extended, path + it) }
    }

    private fun isAvailable(node: String, path: List<String>, extended: Boolean) = if (extended) {
        when {
            node[0] in 'A'..'Z' -> true
            node == "start"  -> false
            else -> noDoubles(path) || node !in path
        }
    } else {
        node[0] in 'A'..'Z' || node !in path
    }

    private fun noDoubles(path: List<String>): Boolean {
        val smallCaves = path.filter { it[0] !in 'A'..'Z' }
        return smallCaves.size == smallCaves.toSet().size
    }
}
