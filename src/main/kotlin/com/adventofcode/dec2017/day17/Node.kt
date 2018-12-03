package com.adventofcode.dec2017.day17

class Node(val value: Int, var next: Node? = null) {

    fun insert(value: Int): Node {
        val new = Node(value, next)
        next = new
        return new
    }

    tailrec fun toList(start: Node? = this, list: MutableList<Int> = mutableListOf()): List<Int> = if (start == null) list else {
        list.add(start.value)
        toList(start.next, list)
    }

    override fun toString() = toList().joinToString(" ")
}