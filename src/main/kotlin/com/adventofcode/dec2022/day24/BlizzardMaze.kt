package com.adventofcode.dec2022.day24

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.util.LinkedList

class BlizzardMaze(input: List<String>) {

    val height = input.size - 2
    val width = input[0].length - 2
    val entry = Position(input[0].indexOf('.') - 1, -1)
    val exit = Position(input.last().indexOf('.') - 1, height)

    val blizzards: List<Blizzard> = input.drop(1)
        .dropLast(1)
        .flatMapIndexed { y, row ->
            row.substring(1 until row.length - 1).withIndex().mapNotNull { (x, c) ->
                if (c != '.') {
                    parseBlizzard(x, y, c)
                } else null
            }
        }

    private fun parseBlizzard(x: Int, y: Int, c: Char): Blizzard = Blizzard(
        when (c) {
            '>' -> Direction.RIGHT
            '<' -> Direction.LEFT
            'v' -> Direction.DOWN
            '^' -> Direction.UP
            else -> error("Unknown value: $c")
        }, Position(x, y)
    )

    fun findPath(): Int {
        val queue = LinkedList<Path>()
        queue.add(Path(listOf(entry)))
        while (queue.isNotEmpty()) {
            val path = queue.poll()
            val current = path.positions.last()
            queue.addAll(Direction.values().mapNotNull {
                val next = path.positions.last() + it
                when {
                    next == exit -> {
                        printPath(path)
                        return path.positions.size
                    }

                    isValid(next, path.positions.size) -> {
                        Path(path.positions + next)
                    }

                    else -> null
                }
            })
            if (isValid(current, path.positions.size)) {
                queue.add(Path(path.positions + current))
            }
        }
        return Int.MAX_VALUE
    }

    fun printPath(path: Path) {
        path.positions.forEachIndexed { index, position ->
            printStep(position, index)
            blizzards.forEachIndexed { blizz, it ->
                println("$blizz: ${it.direction} \t ${it.position(index)}")
            }
        }
    }

    fun Blizzard.position(time: Int) = (position + direction * time) % Position(width, height)

    fun printStep(position: Position, time: Int) {
        println(time)
        for (i in -1..width) {
            print(if (i == entry.x) " " else "#")
        }
        println()
        for (y in 0 until height) {
            print("#")
            for (x in 0 until width) {
                val blizz =
                    blizzards.count { it.position(time) == Position(x, y) }
                print(if (blizz > 0) blizz else if (Position(x, y) == position) "E" else " ")
            }
            println("#")
        }
        for (i in -1..width) {
            print(if (i == exit.x) " " else "#")
        }
        println()
    }

    fun Direction.toCode() = when (this) {
        Direction.LEFT -> "<"
        Direction.RIGHT -> ">"
        Direction.UP -> "^"
        Direction.DOWN -> "v"
    }

    private fun isValid(position: Position, time: Int): Boolean =
        position.x in 0 until width
                && position.y in 0 until height
                && blizzards.none { it.position(time) == position }

    operator fun Direction.times(i: Int) = Position(x * i, y * i)

    operator fun Position.rem(position: Position) = Position(x % position.x, y % position.y)
}