package com.adventofcode.dec2021.day13

import com.adventofcode.positioning.Position

class PageFolder(input: List<String>) {

    var page: Array<BooleanArray>

    var folds: List<String>

    init {
        val points = input.takeWhile { it.isNotBlank() }.map {
            val (x, y) = it.split(",")
            Position(x.toInt(), y.toInt())
        }
        page = Array(points.maxOf { it.y } + 1) { y ->
            BooleanArray(points.maxOf { it.x } + 1) { x ->
                Position(x, y) in points
            }
        }
        folds = input.drop(points.size + 1)
    }

    fun fold(instruction: String) {
        val foldPosition = instruction.drop(13).toInt()
        when (instruction[11]) {
            'y' -> foldVertically(foldPosition)
            'x' -> foldHorizontally(foldPosition)
        }
    }

    fun foldAll() {
        folds.forEach { instruction ->
            fold(instruction)
        }
    }


    private fun foldVertically(foldPosition: Int) {
        page = Array(maxOf(foldPosition, page.size - foldPosition - 1)) { y ->
            BooleanArray(page[y].size) { x ->
                page[y][x] || page[2 * foldPosition - y][x]
            }
        }
    }

    private fun foldHorizontally(foldPosition: Int) {
        page = Array(page.size) { y ->
            BooleanArray(maxOf(foldPosition, page[y].size - foldPosition - 1)) { x ->
                page[y][x] || page[y][2 * foldPosition - x]
            }
        }
    }

    fun printPage(): String = page.joinToString("\n") { it.joinToString("") { if (it) "#" else " " } }

    fun countDots(): Int = page.sumOf { row -> row.count { x -> x } }
}
