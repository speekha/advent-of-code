package com.adventofcode.dec2016.day22

data class StorageNode(val x: Int, val y: Int, val used: Int, val free: Int) {

    companion object {
        fun parse(input: List<String>) : List<StorageNode> = input.drop(2).map {
            parse(it)
        }

        fun parse(input: String) : StorageNode {
            val (id, _, used, free) = input.split("\\s+".toRegex())
            val (_, x, y) = id.split("-")
            return StorageNode(x.drop(1).toInt(), y.drop(1).toInt(), used.dropLast(1).toInt(), free.dropLast(1).toInt())
        }
    }
}
