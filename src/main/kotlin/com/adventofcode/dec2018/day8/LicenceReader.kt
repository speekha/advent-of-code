package com.adventofcode.dec2018.day8

import java.io.File
import java.util.*

class LicenceReader(input: String) {

    val root: Node

    init {
        val numbers = LinkedList<Int>().apply {
            addAll(input.split(" ").map { it.toInt() })
        }
        root = readNode(numbers)
    }

    fun sumMetadata() = sumMetaData(root)

    private fun sumMetaData(node: Node): Int {
        return node.metadata.sum() + node.children.sumBy { sumMetaData(it) }
    }

    private fun readNode(numbers: LinkedList<Int>): Node {
        val childNumber = numbers.poll()
        val metadataNumber = numbers.poll()
        val children = (1..childNumber).map { readNode(numbers) }
        val metadata = (1..metadataNumber).map { numbers.poll() }
        return Node(children, metadata)
    }

    fun computeRootNodeValue(): Int {
        return computeNodeValue(root)
    }

    private fun computeNodeValue(node: Node): Int {
        return if (node.children.isEmpty())
            node.metadata.sum()
        else
            node.metadata.map {
                (it - 1).takeIf { it in node.children.indices }
                        ?.let { computeNodeValue(node.children[it]) }
                ?: 0
            }.sum()
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day8/input.txt").readLines()[0]
    val reader = LicenceReader(input)
    println("Proper order: ${reader.sumMetadata()}")
    println("Proper order: ${reader.computeRootNodeValue()}")

}