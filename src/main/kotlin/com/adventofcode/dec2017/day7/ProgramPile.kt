package com.adventofcode.dec2017.day7

import java.io.File

class ProgramPile(input: List<String> = emptyList()) {

    val nodes = parseInputs(input).associate { it.name to it }

    val tree = buildTree(input)

    fun node(name: String) = nodes[name] ?: error("$name not found")

    fun findRoot(): Node {
        return node(findRoot(tree.keys.first()))
    }

    fun correctWeight(): Int {
        val unbalanced = findUnbalancedProgram()
        val parent = node(tree[unbalanced.name] ?: "")
        val sibling = parent.children.map { node(it) }.filter { it != unbalanced }
        return sibling.first().totalWeight - unbalanced.addChildrenWeights()
    }

    fun findUnbalancedProgram(): Node {
        return findUnbalancedProgram(findRoot())
    }

    tailrec fun findRoot(node: String): String {
        val parent = tree[node]
        return if (parent == null) {
            node
        } else {
            findRoot(parent)
        }
    }

    tailrec fun findUnbalancedProgram(root: Node): Node {
        val subtowerWeights = root.children.groupBy { node(it).totalWeight() }
        val unbalanced = subtowerWeights.minBy { it.value.size }?.value?.first() ?: ""
        val node = node(unbalanced)
        return if (node.areChildrenBalanced()) node else findUnbalancedProgram(node)
    }

    fun Node.areChildrenBalanced() = children.groupBy { node(it).totalWeight() }.size == 1

    fun Node.addChildrenWeights() = children.sumBy { node(it).totalWeight() }

    fun getChildrenWeight(node: String) = node(node).addChildrenWeights()

    fun Node?.totalWeight(): Int = when {
        this == null -> 0
        totalWeight != 0 -> totalWeight
        else -> (weight + children.sumBy { node(it).totalWeight() }).also { totalWeight = it }
    }

    fun getTotalWeight(node: String) = node(node).totalWeight()

    fun buildTree(input: List<String>) = parseInputs(input)
            .flatMap { parent ->
                parent.children.map { it to parent.name }
            }.toMap()

    fun parseInputs(input: List<String>) = input.map { parseInputLine(it) }

    fun parseInputLine(input: String): Node {
        val split = input.split(" -> ")
        val parent = split[0]
        val children = if (split.size > 1) split[1] else null
        val (name, weight, _) = parent.split(" ")
        return Node(name, weight.substring(1, weight.length - 1).toInt(), children?.split(", ") ?: emptyList())
    }

    data class Node(
            val name: String,
            val weight: Int,
            val children: List<String>
    ) {
        var totalWeight: Int = 0
    }
}

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day7/input.txt").readLines()
    with(ProgramPile(input)) {
        println("Root: ${findRoot().name}")
        println("Correct weight ${findUnbalancedProgram().name}: ${correctWeight()}")
    }
}