package com.adventofcode.dec2017.day14

import com.adventofcode.dec2017.day10.KnotHasher
import java.util.*

class DefragMonitor(val input: String) {

    fun countUsedSquares(): Int = (0..127).map { displayRow(it) }.sumOf { it.count { it == '1' } }

    fun countRegions(): Int {
        return countRegions((0..127).map {
            val row = displayRow(it)
            BooleanArray(row.length) { row[it] == '1' }
        }.toTypedArray())
    }

    fun countRegions(rows: Array<BooleanArray>): Int {
        var i = 0
        while (fillOneRegion(rows)) {
            i++
        }
        return i
    }

    fun fillOneRegion(rows: Array<BooleanArray>): Boolean {
        val cell = findFirstUsedSquare(rows)
        return if (cell.first != -1) {
            fillRegion(rows, cell)
        } else {
            false
        }
    }

    private tailrec fun findFirstUsedSquare(
        rows: Array<BooleanArray>,
        x: Int = -1, y: Int = -1
    ): Pair<Int, Int> = if (x != -1 || y >= rows.size - 1) {
        x to y
    } else {
        findFirstUsedSquare(rows, rows[y + 1].indexOfFirst { it }, y + 1)
    }

    private fun fillRegion(rows: Array<BooleanArray>, cell: Pair<Int, Int>): Boolean {
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(cell)
        while (queue.isNotEmpty()) {
            val (x, y) = queue.pop()
            rows[y][x] = false
            if (y > 0 && rows[y - 1][x]) {
                queue.add(x to y - 1)
            }
            if (x > 0 && rows[y][x - 1]) {
                queue.add(x - 1 to y)
            }
            if (x < rows.size - 1 && rows[y][x + 1]) {
                queue.add(x + 1 to y)
            }
            if (y < rows.size - 1 && rows[y + 1][x]) {
                queue.add(x to y + 1)
            }
        }
        return true
    }

    fun computeRow(row: Int) = KnotHasher(256, 64).digest(getKey(row))

    fun displayRow(row: Int): String = computeRow(row).joinToString("") {
        Integer.toBinaryString(it).padStart(8, '0')
    }

    fun getKey(row: Int) = "$input-$row"
}