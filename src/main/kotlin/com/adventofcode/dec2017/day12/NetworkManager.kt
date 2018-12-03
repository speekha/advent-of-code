package com.adventofcode.dec2017.day12

import java.io.File
import java.util.*

class NetworkManager {

    lateinit var programs: Map<Int, List<Int>>

    fun parse(input: List<String>) {
        programs = input.map { parseEntry(it) }
                .groupBy { it.first }
                .mapValues { it.value.first().second }
    }

    private fun parseEntry(input: String): Pair<Int, List<Int>> {
        val (id, siblings) = input.split(" <-> ")
        return id.toInt() to siblings.split(", ").map { it.toInt() }
    }

    fun browseGroup(id: Int): List<Int> {
        val processed = mutableMapOf<Int, Boolean>()
        val queue = LinkedList<Int>()
        var count = 0
        queue.add(id)
        while(queue.isNotEmpty()) {
            val pop = queue.pop()
            processed[pop] = true
            count++
            queue.addAll(programs[pop]?.filter { !queue.contains(it) && processed[it] != true } ?: emptyList())
        }
        return processed.keys.toList()
    }

    fun countGroups(): Int {
        var groupCount = 0
        val backlog = programs.keys.toMutableList()
        while (backlog.isNotEmpty()) {
            backlog -= browseGroup(backlog.first())
            groupCount++
        }
        return groupCount
    }
}