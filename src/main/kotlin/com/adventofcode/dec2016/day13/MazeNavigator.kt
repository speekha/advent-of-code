package com.adventofcode.dec2016.day13

import java.util.*

class MazeNavigator(val offset: Int) {

    fun isWall(x: Int, y: Int): Boolean {
        val z = x * x + 3 * x + 2 * x * y + y + y * y + offset
        return Integer.toBinaryString(z).count { it == '1' } % 2 == 1
    }

    fun findDistance(start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        val map = mutableMapOf<Pair<Int, Int>, Int>()
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(start)
        map[start] = 0
        while (queue.isNotEmpty()) {
            val pop = queue.pop()
            val (x, y) = pop
            val steps = map[x to y] ?: 0
            if (pop == end) {
                return steps
            }
            if (checkCell(x, y - 1, map)) {
                queue.add(x to y - 1)
                map[x to y - 1] = steps + 1
            }
            if (checkCell(x - 1, y, map)) {
                queue.add(x - 1 to y)
                map[x - 1 to y] = steps + 1
            }
            if (checkCell(x + 1, y, map)) {
                queue.add(x + 1 to y)
                map[x + 1 to y] = steps + 1
            }
            if (checkCell(x, y + 1, map)) {
                queue.add(x to y + 1)
                map[x to y + 1] = steps + 1
            }
        }
        return -1
    }

    fun countReachable(start: Pair<Int, Int>, max: Int): Int {
        val map = mutableMapOf<Pair<Int, Int>, Int>()
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(start)
        map[start] = 0
        var steps = 0
        while (queue.isNotEmpty() && steps <= max) {
            val pop = queue.pop()
            val (x, y) = pop
            steps = map[x to y] ?: 0
            if (steps < max) {
                if (checkCell(x, y - 1, map)) {
                    queue.add(x to y - 1)
                    map[x to y - 1] = steps + 1
                }
                if (checkCell(x - 1, y, map)) {
                    queue.add(x - 1 to y)
                    map[x - 1 to y] = steps + 1
                }
                if (checkCell(x + 1, y, map)) {
                    queue.add(x + 1 to y)
                    map[x + 1 to y] = steps + 1
                }
                if (checkCell(x, y + 1, map)) {
                    queue.add(x to y + 1)
                    map[x to y + 1] = steps + 1
                }
            }
        }
        return map.count { it.value >= 0 }
    }

    fun checkCell(x: Int, y: Int, map: MutableMap<Pair<Int, Int>, Int>): Boolean {
        return if (x < 0 || y < 0 || map.contains(x to y)) {
            false
        } else {
            val wall = isWall(x, y)
            if (wall) {
                map[x to y] = -1
            }
            !wall
        }
    }
}

fun main() {
    with(MazeNavigator(1358)) {
        println("Shortest path: ${findDistance(1 to 1, 31 to 39)}")
        println("Cells within 50 steps: ${countReachable(1 to 1, 50)}")
    }
}