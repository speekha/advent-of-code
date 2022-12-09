package com.adventofcode.dec2022.day07

class FileSystem {

    val root: Dir = Dir("/")

    private var current = root

    fun parseOutput(input: List<String>) {
        input.forEach { line ->
            when {
                line.startsWith("$ cd") -> changeDirectory(line.drop(5))
                line.startsWith("$ ls") -> {}
                else -> addNode(line)
            }
        }

    }

    private fun addNode(line: String) {
        val (info, name) = line.split(" ")
        current.files += if (info == "dir") Dir(name).apply { parent = current } else File(name, info.toInt())
    }

    private fun changeDirectory(dir: String) {
        current = when (dir) {
            ".." -> current.parent ?: error("Invalid parent")
            "/" -> root
            else -> current.files.first { it is Dir && it.name == dir } as Dir
        }
    }

    fun sumEligibleDirs(dir: Dir = root): Int {
        var result = 0
        val size = dir.size
        if (size < 100000) {
            result += size
        }
        return result + dir.files.sumOf { if (it is Dir) sumEligibleDirs(it) else 0 }
    }

    fun freeUpSpace(): Int {
        val availableSpace = 70000000 - root.size
        val neededSpace = 30000000 - availableSpace
        val dirs = listDirs(root).filter { it.size >= neededSpace }.minBy { it.size }
        return dirs.size
    }

    private fun listDirs(dir: Dir): List<Dir> = dir.files.flatMap {
        if (it is Dir) listDirs(it) else listOf()
    } + dir
}