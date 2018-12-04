package com.adventofcode.dec2019.day15

import com.adventofcode.positioning.CardinalDirection
import com.adventofcode.positioning.Position
import java.util.*

class Explorer(
        private val robot: CommandInterface
) {
    suspend fun findOxygen(): List<Position> = mapArea(false)

    suspend fun mapArea(mapAll: Boolean): LinkedList<Position> {
        val path = LinkedList<Position>()
        var position = robot.position
        path.push(position)
        while (mapAll || robot.oxygen == null) {
            var direction = CardinalDirection.values().firstOrNull { robot.map[position + it] == null }
            if (direction == null) {
                path.pop()
                if (path.isEmpty()) {
                    break
                }
                val target = path.pop()
                direction = CardinalDirection.values().first { position + it == target }
            }
            robot.move(direction)
            if (robot.position != position) {
                position = robot.position
                path.push(position)
            }
        }
        return path
    }

    fun fillOxygen(): Int {
        var i = 0
        var map: Map<Position, CellType> = robot.map
        while (map.containsValue(CellType.EMPTY)) {
            map = map.mapValues { entry ->
                if (entry.value == CellType.EMPTY && CardinalDirection.values().any { map[entry.key + it] == CellType.OXYGEN }) {
                    CellType.OXYGEN
                } else {
                    entry.value
                }
            }
            i++
        }
        return i
    }

    fun printMap(map: Map<Position, CellType> = robot.map) {
        val minX = (robot.map.keys.minByOrNull { it.x } ?: error("No min X")).x
        val maxX = (robot.map.keys.maxByOrNull { it.x } ?: error("No max X")).x
        val minY = (robot.map.keys.minByOrNull { it.y } ?: error("No min Y")).y
        val maxY = (robot.map.keys.maxByOrNull { it.y } ?: error("No max Y")).y
        val render = (minY..maxY).joinToString("\n") { y ->
            (minX..maxX).joinToString("") { x ->
                when (map [Position(x, y)]) {
                    CellType.WALL -> "#"
                    CellType.EMPTY -> "."
                    CellType.OXYGEN -> "O"
                    else -> " "
                }
            }
        }
        println(render)
    }

}
