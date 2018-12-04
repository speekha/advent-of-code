package com.adventofcode.dec2018.day15

import java.util.*

class FightSimulator(input: List<String>) {

    val grid = input.map {
        it.toCharArray()
    }.toTypedArray()

    val fighters: List<Fighter> = input.mapIndexed { y, row ->
        row.mapIndexedNotNull { x, c ->
            when (c) {
                'G' -> Fighter.Goblin(Position(x, y))
                'E' -> Fighter.Elf(Position(x, y))
                else -> null
            }
        }
    }.flatten()

    var player = 0

    fun findTargets(i: Int): List<Fighter> = fighters.filter { it::class != fighters[i]::class }
            .sortedWith(kotlin.Comparator { o1, o2 ->
                val distance1 = o1.position - fighters[i].position
                val distance2 = o2.position - fighters[i].position
                if (distance1 == distance2)
                    if (o1.position.y == o2.position.y)
                        o1.position.x - o2.position.x
                    else o1.position.y - o2.position.y
                else
                    distance1 - distance2
            })

    fun iterate() {
        val targets = findTargets(player)
        if (targets.size == 0) {
            // No more enemies
            return
        } else {
            if (targets[0].position - fighters[player].position == 1) {
                // Attack

            } else {
                // Move
                val enemy = findClosestEnemy()
                fighters[player].position += enemy
            }
            player++
        }
    }

    private fun findClosestEnemy(): Direction {
        val map = Array(grid.size) { y ->
            grid[y].map { c ->
                when (c) {
                    '.' -> Int.MAX_VALUE
                    else -> -1
                } to Direction.RIGHT
            }.toTypedArray()
        }

        val queue = LinkedList<Position>()
        val currentPlayer = fighters[player]
        Direction.values().forEach { dir ->
            val next = currentPlayer.position + dir
            if (map[next.y][next.x].first > 0) {
                map[next.y][next.x] = 1 to dir
                queue.add(next)
            }
        }

        while (queue.isNotEmpty()) {
            val position = queue.poll()
            val distance = map[position.y][position.x].first + 1
            val first = map[position.y][position.x].second
            Direction.values().forEach { dir ->
                val next = position + dir
                if (grid[next.y][next.x] == currentPlayer.enemy) {
                    return first
                } else {
                    if (map[next.y][next.x].first > distance) {
                        map[next.y][next.x] = distance to first
                        queue.add(next)
                    }
                }
            }
        }
        error("No enemy found")
    }

}