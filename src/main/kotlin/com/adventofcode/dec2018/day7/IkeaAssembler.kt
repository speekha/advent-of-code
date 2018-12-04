package com.adventofcode.dec2018.day7

import java.io.File

class IkeaAssembler(input: List<String>) {

    val edges = mutableMapOf<String, Instruction>()

    init {
        input.forEach {
            val from = it.substring(5, 6)
            val to = it.substring(36, 37)
            addEdge(from, to)
        }

    }

    operator fun get(key: String): Instruction = edges[key] ?: Instruction(key).also { edges[key] = it }

    fun addEdge(from: String, to: String) {
        val fromInst = get(from)
        fromInst.nextSteps += to
        val toInst = get(to)
        toInst.prerequisite += from
    }


    fun assemble(): String {
        val path = mutableListOf<String>()
        while (edges.isNotEmpty()) {
            edges.values.filter { it.prerequisite.isEmpty() }.map { it.id }.minOrNull()?.let { root ->
                path += root
                edges.remove(root)
                edges.forEach { _, inst -> inst.prerequisite.remove(root) }
            }
        }
        return path.joinToString("")
    }

    fun optimizeAssembly(workerPool: Int, overhead: Int): Int {
        val path = mutableListOf<String>()
        val workers = mutableListOf<Pair<String, Int>>()
        var time = 0

        do {
            workers.minByOrNull { it.second }?.let { (_, completeAt) ->
                time = completeAt
                val completing = workers.filter { it.second == time }.map { it.first }
                completing.forEach { task ->
                    edges.forEach { _, inst -> inst.prerequisite.remove(task) }
                }
                workers.removeAll { it.first in completing }
                path.addAll(completing)
            }

            val roots = edges.values
                    .filter { it.prerequisite.isEmpty() }
                    .map { it.id }
                    .sorted()
                    .take(workerPool - workers.size)

            roots.forEach {
                workers += it to (time + overhead + (it[0] - 'A'))
                edges.remove(it)
            }
        } while (edges.isNotEmpty())

        path.add(workers[0].first)
        return workers.map { it.second }.maxOrNull() ?: 0
    }

}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day7/input.txt").readLines()
    var assembler = IkeaAssembler(input)
    println("Proper order: ${assembler.assemble()}")
    assembler = IkeaAssembler(input)
    println("Optimized processing: ${assembler.optimizeAssembly(5, 61)}")
}
