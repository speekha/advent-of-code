package com.adventofcode.dec2022.day07

sealed class Node {
    abstract val size: Int
}

data class File(
    val name: String,
    override val size: Int
) : Node()

data class Dir(
    val name: String,
    val files: MutableSet<Node> = mutableSetOf()
) : Node() {

    var parent: Dir? = null

    override fun equals(other: Any?): Boolean = toString() == other.toString()

    override val size: Int
        get() = files.sumOf { it.size }
}
