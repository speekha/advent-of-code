package com.adventofcode.dec2016.day22

import java.io.File

class NodeHandler(val nodeList: List<StorageNode>) {

    val grid = nodeList.associateBy { node -> Pair(node.x, node.y) }

    fun findViablePairs() = nodeList.flatMap{ a ->
        nodeList.filter { b ->
            b.used < a.free && b.used > 0 && (a.x != b.x || a.y != b.y)
        }.map { Pair(a, it) }
    }

    fun countViablePairs() = findViablePairs().size
    fun accessData(): Int {
        return 7
    }

}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day22/input.txt").readLines()
    val nodes = StorageNode.parse(input)
    println("Viable pairs: ${NodeHandler(nodes).countViablePairs()}")
}