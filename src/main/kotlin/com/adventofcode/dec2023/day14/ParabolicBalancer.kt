package com.adventofcode.dec2023.day14

class ParabolicBalancer(input: List<String>) {

    var platform = mapInput(input)

    private fun mapInput(input: List<String>) = input.map {
            it.map {
                when (it) {
                    'O' -> Cell.ROUND_ROCK
                    '#' -> Cell.SQUARE_ROCK
                    else -> Cell.EMPTY
                }
            }.toTypedArray()
        }.toTypedArray()

    fun tiltNorth() {
        for (row in 1..platform.lastIndex) {
            platform[row].indices.forEach { col ->
                if (platform[row][col] == Cell.ROUND_ROCK) {
                    var i = row
                    while (i > 0 && platform[i - 1][col] == Cell.EMPTY) {
                        i--
                    }
                    if (i >= 0 && platform[i][col] == Cell.EMPTY) {
                        platform[row][col] = Cell.EMPTY
                        platform[i][col] = Cell.ROUND_ROCK
                    }
                }
            }
        }
    }

    fun tiltWest() {
        for (col in 1..platform[0].lastIndex) {
            platform.indices.forEach { row ->
                if (platform[row][col] == Cell.ROUND_ROCK) {
                    var i = col
                    while (i > 0 && platform[row][i - 1] == Cell.EMPTY) {
                        i--
                    }
                    if (i >= 0 && platform[row][i] == Cell.EMPTY) {
                        platform[row][col] = Cell.EMPTY
                        platform[row][i] = Cell.ROUND_ROCK
                    }
                }
            }
        }
    }

    fun tiltSouth() {
        for (row in platform.lastIndex - 1 downTo 0) {
            platform[row].indices.forEach { col ->
                if (platform[row][col] == Cell.ROUND_ROCK) {
                    var i = row
                    while (i < platform.lastIndex && platform[i + 1][col] == Cell.EMPTY) {
                        i++
                    }
                    if (i <= platform.lastIndex && platform[i][col] == Cell.EMPTY) {
                        platform[row][col] = Cell.EMPTY
                        platform[i][col] = Cell.ROUND_ROCK
                    }
                }
            }
        }
    }

    fun tiltEast() {
        for (col in platform[0].lastIndex - 1 downTo 0) {
            platform.indices.forEach { row ->
                if (platform[row][col] == Cell.ROUND_ROCK) {
                    var i = col
                    while (i < platform[row].lastIndex && platform[row][i + 1] == Cell.EMPTY) {
                        i++
                    }
                    if (i <= platform[row].lastIndex && platform[row][i] == Cell.EMPTY) {
                        platform[row][col] = Cell.EMPTY
                        platform[row][i] = Cell.ROUND_ROCK
                    }
                }
            }
        }
    }

    fun weigh(): Int {
        return platform.indices.sumOf { row ->
            platform[row].count { it == Cell.ROUND_ROCK } * (platform.size - row)
        }
    }

    override fun toString(): String = platform.joinToString("\n") {
        it.joinToString("") { cell -> cell.label.toString() }
    }

    fun cycleAndWeigh(count: Int): Int {
        val cache = mutableMapOf<String, Int>()
        cache[toString()] = 0
        var i = 0
        while (i < count) {
            i++
            if (i % 100 == 0) {
                println(i / 100)
            }
            cycle()
            val result = toString()
            val value = cache[result]
            if (value != null) {
                val iteration = value + ((count - i) % (value - i))
                val entry = cache.entries.first { it.value == iteration }
                platform = mapInput(entry.key.split("\n"))
                break
            } else {
                cache[result] = i
            }
        }
        return weigh()
    }

    fun cycle() {
        tiltNorth()
        tiltWest()
        tiltSouth()
        tiltEast()
    }

    enum class Cell(val label: Char) {
        EMPTY('.'), ROUND_ROCK('O'), SQUARE_ROCK('#')
    }

}
