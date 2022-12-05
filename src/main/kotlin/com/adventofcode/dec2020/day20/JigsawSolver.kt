package com.adventofcode.dec2020.day20

class JigsawSolver(input: List<String>) {

    val tiles: Map<Int, Tile> = input.filter { it.isNotEmpty() }
        .chunked(11)
        .map { parseTile(it) }
        .associateBy { it.id }

    private val indexes: Map<String, List<Int>> = tiles.values
        .flatMap { tile -> (tile.sides + tile.sides.map { it.reversed() }).map { it to tile } }
        .groupBy { it.first }
        .mapValues { it.value.map { pair -> pair.second.id } }

    private val outerEdges: Map<String, Int> = indexes.filterValues { it.size == 1 }.mapValues { it.value[0] }

    private val outerEdgeTiles: Map<Int, List<String>> = outerEdges.entries
        .groupBy { it.value }
        .mapValues { entry -> entry.value.map { it.key } }

    val corners = tiles.values.filter { tile -> outerEdgeTiles[tile.id]?.size == 4 }

    private fun parseTile(data: List<String>): Tile {
        val id = data[0].drop(5).dropLast(1).toInt()
        return Tile(id, data.drop(1))
    }

    fun findNeighbors(id: Int): List<Int> {
        val tile = tiles[id] ?: error("Not found")
        return tile.sides.flatMap { indexes[it] ?: emptyList() }.filter { it != id }
    }

    fun assembleJigsaw(): List<List<Tile>> {
        val stops = corners.map { it.id }.toSet()
        var nextRow: List<Tile> = buildRow(selectFirstCorner())
        val image = mutableListOf(nextRow)
        do {
            var firstTile = nextRow[0].neighbor(BOTTOM)
            firstTile = firstTile.normalize(nextRow[0].sides[BOTTOM].reversed(), TOP)
            nextRow = buildRow(firstTile)
            image += nextRow
        } while (nextRow[0].id !in stops)
        return image
    }

    fun buildImage(): List<String> {
        val merged = assembleJigsaw()
        return merged.flatMap { row ->
            val blocks = row.map { col ->
                col.data.drop(1).dropLast(1).map { it.substring(1, it.length - 1) }
            }
            blocks[0].indices.map { i ->
                blocks.joinToString("") { it[i] }
            }
        }
    }

    private fun selectFirstCorner(): Tile {
        var tile = corners.first().flip()
        val outerSides = tile.sides.withIndex().filter { outerEdges.containsKey(it.value) }.map { it.index }.sorted()
        val leftEdge = if (outerSides[0] == outerSides[1] - 1) outerSides[0] else LEFT
        tile = tile.normalize(tile.sides[leftEdge], LEFT)
        return tile
    }

    private fun buildRow(firstTile: Tile): List<Tile> {
        var tile = firstTile
        val row = mutableListOf(tile)
        var side: String = tile.sides[1].reversed()
        do {
            tile = tile.neighbor(RIGHT)
            tile = tile.normalize(side, LEFT)
            row += tile
            side = tile.sides[1].reversed()
        } while (!outerEdges.containsKey(side))
        return row
    }

    private fun Tile.neighbor(side: Int) =
        tiles[(indexes[sides[side]] ?: error("Side not found")).first { it != id }] ?: error("Couldn't find neighbor")

    private fun Tile.normalize(reference: String, side: Int): Tile {
        var result = this
        var edge = result.sides.indexOf(reference)
        if (edge == -1) {
            result = result.flip()
            edge = result.sides.indexOf(reference)
        }
        val rotations = (edge + 4 - side) % 4
        repeat(rotations) {
            result = result.rotateLeft()
        }
        return result
    }

    fun countSeaMonsters(): Pair<List<String>, Int> {
        var map = buildImage()
        val results = mutableListOf<Pair<List<String>, Int>>()
        (1..4).map {
            val test = locateSeaMonsters(map)
            if (test.second > 0) {
                return test
            }
            map = map.rotateLeft()
        }
        map = map.flip()
        repeat((1..4).count()) {
            val test = locateSeaMonsters(map)
            if (test.second > 0) {
                return test
            }
            map = map.rotateLeft()
        }
        println("Results : ${results.map { it.second }}")
        return results.maxByOrNull { it.second } ?: error("No monster found")
    }

    private fun locateSeaMonsters(map: List<String>): Pair<List<String>, Int> {
        val resultMap = map.toTypedArray()
        var count = 0
        for (row in 0..map.size - 3) {
            for (col in 0..map[row].length - 20) {
                if (hashes.all { map[row + it.first][col + it.second] == '#' }) {
                    count++
                    (0..2).forEach { i ->
                        resultMap[row + i] = replaceMonster(resultMap[row + i], col..col + 19, i, row + 1)
                    }
                }
            }
        }
        return resultMap.toList() to count
    }

    private fun replaceMonster(line: String, position: IntRange, monsterLine: Int, lineId: Int): String =
        line.take(position.first) +
                line.substring(position).revealMonster(seaMonster[monsterLine], lineId) +
                line.drop(position.last + 1)

    private fun String.revealMonster(monster: String, lineId: Int): String =
        zip(monster) { a, b ->
            if (b == '#')
                if (a == '#') 'O' else error("Mismatch @$lineId! $this => $monster")
            else a
        }.joinToString("")

    fun computeRoughness(): Int {
        val map = countSeaMonsters().first
        map.forEachIndexed { index, str -> println("$index : $str") }
        return map.sumOf { row -> row.count { it == '#' } }
    }

    data class Match(
        val index: Int,
        val data: String,
        val position: IntRange,
    )

    companion object {
        const val TOP = 0
        const val RIGHT = 1
        const val BOTTOM = 2
        const val LEFT = 3

        private val seaMonster = arrayOf(
            "..................#.",
            "#....##....##....###",
            ".#..#..#..#..#..#..."
        )

        val hashes = seaMonster.mapIndexed { rowId, row ->
            row.mapIndexedNotNull { colId, char ->
                Pair(rowId, colId).takeIf { char == '#' }
            }
        }.flatten()

        val seaMonsterRegex = seaMonster.map { Regex(it) }.toTypedArray()
    }
}
