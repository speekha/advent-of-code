package com.adventofcode.dec2022.day22

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position

class PasswordGenerator(
    input: List<String>,
    val cubeSize: Int = 0
) {

    private val lineSize = input.maxOf { it.length }

    val map: List<String> = input.map {
        it.padEnd(lineSize, ' ')
    }

    var direction: Direction = Direction.RIGHT
        private set

    var position: Position = Position(map[0].indexOf('.'), 0)
        private set

    fun move(sequence: String) {
        var steps = 0
        sequence.forEach { c ->
            steps = if (c in '0'..'9') {
                10 * steps + (c - '0')
            } else {
                move(steps)
                when (c) {
                    'R' -> turnRight()
                    'L' -> turnLeft()
                }
                0
            }
        }
        move(steps)
    }

    fun move(steps: Int) {
        var i = steps
        do {
            val (nextPosition, nextDirection) = if (cubeSize == 0) moveOneTile() else moveOneTileOnACube()
            if (map[nextPosition] == '.') {
                position = nextPosition
                direction = nextDirection
            }
            i--
        } while (i > 0 && map[nextPosition] != '#')
    }

    operator fun List<String>.get(position: Position) = map[position.y][position.x]
    fun turnRight() {
        direction = direction.turnRight()
    }

    fun turnLeft() {
        direction = direction.turnLeft()
    }

    private fun moveOneTile(): Pair<Position, Direction> {
        var next: Position = position
        do {
            var (x, y) = next + direction
            if (y >= map.size) {
                y -= map.size
            } else if (y < 0) {
                y += map.size
            }
            if (x >= map[y].length) {
                x -= map[y].length
            } else if (x < 0) {
                x += map[y].length
            }
            next = Position(x, y)
        } while (map[next] == ' ')
        return next to direction
    }

    private fun moveOneTileOnACube(): Pair<Position, Direction> {
        var next = position + direction
        return if(next.x !in map[0].indices || next.y !in map.indices || map[next] == ' ') {
            when {
                (next.x in 50..99 && next.y == -1) -> TODO("Top center")
                (next.x in 100..149 && next.y == -1) -> TODO("Top right")
                next.x == 150 -> TODO("Right top")
                next.x == 49 && next.y in 0..49 -> TODO("Left top")
                next.x == 49 && next.y in 50..99 -> TODO("Left center")
                next.x in 100..149 && next.y == 50 -> TODO("Right top center")
                next.x == 99 && next.y in 50..99 -> TODO("Right center bottom")
                else -> error("Not supported: $next")
            }
        } else {
             next to direction
        }
    }

    fun computePassword(): Int = 4 * (position.x + 1) + 1000 * (position.y + 1) + ((direction.ordinal + 3) % 4)
}