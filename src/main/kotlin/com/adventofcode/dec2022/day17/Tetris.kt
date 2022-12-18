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


    var height: Int = 0
        private set

    private var rock = Shapes.A

    private var currentJet = 0

    private val chamber = LinkedList<Array<Boolean>>()

    fun dropRock() {
        while (chamber.size < height + 3 + rock.height) {
            chamber.addFirst(Array(7) { true })
        }
        var position = Position(2, 0)
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
        height = chamber.size - position.y
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

    fun print() {
        chamber.forEach { row ->
            println(row.joinToString(separator = "", prefix = "[", postfix = "]") { if (it) " " else "#" })
        }
        println()
    }
}