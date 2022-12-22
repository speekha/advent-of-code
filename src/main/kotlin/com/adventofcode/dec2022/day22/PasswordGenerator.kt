package com.adventofcode.dec2022.day22

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position

class PasswordGenerator(input: List<String>) {

    private val lineSize = input.maxOf { it.length }

    val map: List<String> = input.map {
        it.padEnd(lineSize, ' ')
    }

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
            var next = moveOneTile(position, direction)
            while (map[next] == ' ') {
                next = moveOneTile(next, direction)
            }
            if (map[next] == '.') {
                position = next
            }
            i--
        } while (i > 0 && map[next] != '#')
    }

    var direction: Direction = Direction.RIGHT

    var position: Position = Position(map[0].indexOf('.'), 0)
        private set

    operator fun List<String>.get(position: Position) = map[position.y][position.x]
    fun turnRight() {
        direction = direction.turnRight()
    }

    fun turnLeft() {
        direction = direction.turnLeft()
    }

    fun moveOneTile(position: Position, direction: Direction): Position {
        val next = position + direction
        val nextY = (next.y + map.size) % map.size
        return Position(
            (next.x + map[nextY].length) % map[nextY].length,
            nextY
        )
    }

    fun computePassword(): Int = 4 * (position.x + 1) + 1000 * (position.y + 1) + ((direction.ordinal + 3) % 4)
}