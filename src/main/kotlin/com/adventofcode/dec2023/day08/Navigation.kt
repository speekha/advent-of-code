package com.adventofcode.dec2023.day08

import com.adventofcode.lcm

class Navigation(input: List<String>) {


    val path: String = input[0]

    val network: Map<String, Node> = input.drop(2).associate {
        val name = it.substring(0..2)
        val left = it.substring(7..9)
        val right = it.substring(12..14)
        name to Node(name, left, right)
    }

    fun countSteps(): Int = mapPath("AAA") { it == "ZZZ" }

    fun countGhostSteps(): Long {
        val starts = network.keys.filter { it.endsWith("A") }
        val steps = starts.map { start -> mapPath(start) { node -> node.endsWith("Z")  }.toLong() }
        return steps.fold(1) { acc, value -> lcm(acc, value)}
    }

    private fun mapPath(start: String, stop: (String)-> Boolean) : Int {
        var current = start
        var steps = 0
        while (!stop(current)) {
            current = if (path[steps % path.length] == 'L') network[current]!!.left else network[current]!!.right
            steps++
        }
        return steps
    }
}
