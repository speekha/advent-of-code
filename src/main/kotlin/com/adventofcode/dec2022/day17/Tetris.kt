package com.adventofcode.dec2022.day17

import com.adventofcode.inc
import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import com.adventofcode.positioning.get
import com.adventofcode.positioning.set
import java.util.LinkedList

class Tetris(
    private val jetSequence: String
) {


    private var height: Int = 0

    private var rock = Shapes.A

    private var currentJet = 0

    private val chamber = LinkedList<Array<Boolean>>()

    fun dropRocks(steps: Long): Long {
        primeChamber(steps)
        return if (steps <= 500) {
            height.toLong()
        } else {
            computeHeightWithCycle(steps)
        }
    }

    private fun primeChamber(steps: Long) {
        repeat(steps.coerceAtMost(500).toInt()) {
            dropRock()
        }
    }

    private fun computeHeightWithCycle(steps: Long) : Long {
        val cache = mutableMapOf<Pair<Int, Int>, Pair<Long, Int>>()
        for (i in 501L..steps) {
            val key = currentJet to rock.ordinal
            val previous = cache[key]
            dropRock()
            if (previous == null) {
                cache[key] = i to height
            } else {
                val cycle = i - previous.first
                val remaining = (steps - i) % cycle
                if (remaining == 0L) {
                    val heightDifference = height - previous.second
                    return height + ((steps - i) / cycle) * heightDifference
                }
            }
        }
        return height.toLong()
    }

    fun dropRock() {
        while (chamber.size < height + 3 + rock.height) {
            chamber.addFirst(Array(7) { true })
        }
        var position = Position(2, chamber.size - height - 3 - rock.height)
        var movingDown = true
        do {
            position = moveLaterally(position)
            val newPosition = moveDown(position)
            movingDown = position != newPosition
            position = newPosition
            currentJet = (currentJet + 1) % jetSequence.length
        } while (movingDown)
        rock.blocks.forEach {
            chamber[position + it] = false
        }
        height = chamber.size - chamber.indexOfFirst { row -> row.any { !it } }
        rock++
    }

    private fun isFree(position: Position) =
        position.y in chamber.indices
                && position.x in chamber[position.y].indices
                && chamber[position]

    private fun moveLaterally(position: Position): Position {
        val move = when (jetSequence[currentJet]) {
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> error("Unknown command")
        }
        return move(position, move)
    }


    private fun moveDown(position: Position): Position = move(position, Direction.DOWN)

    private fun move(position: Position, move: Direction): Position {
        val newPosition = position + move
        return if (rock.blocks.all { isFree(newPosition + it) }) {
            position + move
        } else {
            position
        }
    }

    fun print(rock: Shapes? = null, position: Position = Position(0, 0)) {
        chamber.forEachIndexed { y, row ->
            println(row.withIndex().joinToString(separator = "", prefix = "[", postfix = "]") { (x, it) ->
                when {
                    rock != null && rock.blocks.any { it + position == Position(x, y) } -> "o"
                    it -> " "
                    else -> "#"
                }
            })
        }
        println()
    }
}