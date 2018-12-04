package com.adventofcode.dec2020.day20

data class Tile(
    val id: Int,
    val sides: List<String>,
    val data: List<String> = emptyList()
) {

    constructor(
        id: Int,
        data: List<String>
    ) : this(
        id,
        listOf(
            data[0],
            data.map { it.last() }.joinToString(""),
            data.last().reversed(),
            data.map { it.first() }.reversed().joinToString("")
        ),
        data
    )

    fun flip(): Tile = Tile(id, data.flip())
    fun flipV(): Tile = Tile(id, data.reversed())

    fun rotateLeft(): Tile = Tile(id, data.rotateLeft())

    companion object {
        fun hashSide(side: String) = side.replace('.', '0').replace('#', '1').toInt(2)
    }
}

fun List<String>.flip() = map { it.reversed() }

fun List<String>.rotateLeft(): List<String> {
    return get(0).indices.reversed().map { i ->
        joinToString("") { it[i].toString() }
    }
}
