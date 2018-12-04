package com.adventofcode.dec2019.day17

import com.adventofcode.debug
import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.receiveOrNull
import java.lang.StringBuilder

class ScaffoldCalibrator(
        private val camera: Channel<Long>,
        private val control: Channel<Char>
) {

    var scaffoldState: List<String> = listOf()

    fun computeAlignmentParameters(): Int = scaffoldState.indices.flatMap { y ->
        scaffoldState[y].indices
                .map { x -> Position(x, y) }
                .filter { pos -> isIntersection(pos) }
    }.sumBy { it.x * it.y }

    private fun isIntersection(pos: Position): Boolean = pos.x > 0
            && pos.x < scaffoldState[pos.y].length - 1
            && pos.y > 0
            && pos.y < scaffoldState.size - 1
            && scaffoldState[pos.y][pos.x] != '.'
            && scaffoldState[pos.y - 1][pos.x] == '#'
            && scaffoldState[pos.y + 1][pos.x] == '#'
            && scaffoldState[pos.y][pos.x - 1] == '#'
            && scaffoldState[pos.y][pos.x + 1] == '#'


    suspend fun readCamera() {
        val map = mutableListOf<String>()
        var buffer: String
        do {
            buffer = readLine()
            map += buffer
            debug(buffer)
        } while (buffer.isNotEmpty())
        scaffoldState = map.filter { it.isNotEmpty() }
    }

    private suspend fun readLine(): String {
        val buffer = StringBuilder()
        var c: Char?
        do {
            c = camera.receiveCatching().getOrNull()?.toInt()?.toChar()
            if (c != null && c != '\n') {
                buffer.append(c)
            }
        } while (c != '\n')
        return buffer.toString()
    }

    suspend fun traverseScaffolding() {
        readCamera()
        val path = generatePath()
        val commands = splitPath(path) + "n"
        commands.joinToString(separator = "\n", postfix = "\n").forEach {
            control.send(it)
        }
    }

    internal fun splitPath(tokens: List<String>): List<String> {
        val full = tokens.joinToString(",")
        val possibleA = tokens.indices.map { i ->
            tokens.subList(0, i + 1).joinToString(",")
        }.filter { it.isNotEmpty() && it.length < 20 && full.countSubstring(it) > 1 }
        val possibleB = tokens.indices.reversed().map { i ->
            tokens.subList(i, tokens.lastIndex + 1).joinToString(",")
        }.filter { it.isNotEmpty() && it.length < 20 && full.countSubstring(it) > 1 }

        return combineTokens(possibleA, possibleB, full)
    }

    private fun combineTokens(possibleA: List<String>, possibleB: List<String>, full: String): List<String> {
        val options = mutableListOf<List<String>>()
        var a = ""
        var b = ""
        var c = ""
        loops@ for (iA in possibleA) {
            for (iB in possibleB) {
                val iC = extractC(full, iA, iB)
                if (iC != null) {
                    a = iA
                    b = iB
                    c = iC
                    val main = full.replace(a, "A").replace(b!!, "B").replace(c, "C")
                    options += listOf(main, a, b, c)
                }
            }
        }
        return options.minByOrNull { it[0].length } ?: error("No option found")
    }

    private fun extractC(full: String, a: String, b: String): String? {
        val tokens = full.split(a).flatMap { it.split(b) }.filter { it != "," && it.isNotEmpty() }.toSet()
        return if (tokens.size == 1) {
            tokens.first().drop(1).dropLast(1)
        } else {
            val shortest = (tokens.minByOrNull { it.length } ?: error("No shortest token")).drop(1).dropLast(1)
            if (tokens.all { it.replace(shortest, "").replace(",", "").isEmpty() })
                shortest
            else null
        }
    }

    private fun String.countSubstring(needle: String): Int {
        var i = -1
        var index = -1
        do {
            index = indexOf(needle, startIndex = index + 1)
            i++
        } while (index != -1)
        return i
    }

    internal suspend fun sendCommands(commands: String) {
        "$commands\n".forEach { control.send(it) }
    }

    internal fun generatePath(): List<String> {
        val commands = mutableListOf<String>()
        val position = findRobot()
        val initDirection = getRobotOrientation(position)
        val (direction, turns) = orientateRobot(position, initDirection, commands)
        commands += getTurns(turns)
        commands += followScaffolding(position, direction)
        return commands.chunked(2).map { "${it[0]},${it[1]}" }
    }

    private fun findRobot(): Position {
        return scaffoldState.mapIndexed { y, row ->
            Position("[<>^v]".toRegex().find(row)?.range?.first
                    ?: -1, y)
        }.first { it.x != -1 }
    }

    private fun getRobotOrientation(position: Position): Direction {
        return when (scaffoldState[position.y][position.x]) {
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            '^' -> Direction.UP
            'v' -> Direction.DOWN
            else -> error("Incorrect orientation")
        }
    }

    private fun orientateRobot(position: Position, direction: Direction, commands: MutableList<String>): Pair<Direction, Int> {
        var direction1 = direction
        var turns = 0
        while (getCell(position + direction1) != '#') {
            turns++
            direction1++
        }
        return direction1 to turns
    }

    private fun getTurns(turns: Int): List<String> = when (turns) {
        0 -> emptyList()
        1 -> listOf("R")
        2 -> listOf("R", "R")
        3 -> listOf("L")
        else -> error("Incorrect turn")
    }

    private fun followScaffolding(start: Position, startDir: Direction): List<String> {
        val commands = mutableListOf<String>()
        var position = start
        var direction = startDir
        while (true) {
            var steps = 0
            while (getCell(position + direction) == '#') {
                steps++
                position += direction
            }
            commands += steps.toString()
            when {
                getCell(position + (direction + 1)) == '#' -> {
                    commands += "R"
                    direction++
                }
                getCell(position + (direction - 1)) == '#' -> {
                    commands += "L"
                    direction--
                }
                else -> return commands
            }
        }
    }

    private fun getCell(position: Position) = scaffoldState.getOrNull(position.y)?.getOrNull(position.x)
}
