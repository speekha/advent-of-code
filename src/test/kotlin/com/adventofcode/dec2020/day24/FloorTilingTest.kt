package com.adventofcode.dec2020.day24

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FloorTilingTest {

    val input = listOf(
        "sesenwnenenewseeswwswswwnenewsewsw",
        "neeenesenwnwwswnenewnwwsewnenwseswesw",
        "seswneswswsenwwnwse",
        "nwnwneseeswswnenewneswwnewseswneseene",
        "swweswneswnenwsewnwneneseenw",
        "eesenwseswswnenwswnwnwsewwnwsene",
        "sewnenenenesenwsewnenwwwse",
        "wenwwweseeeweswwwnwwe",
        "wsweesenenewnwwnwsenewsenwwsesesenwne",
        "neeswseenwwswnwswswnw",
        "nenwswwsewswnenenewsenwsenwnesesenew",
        "enewnwewneswsewnwswenweswnenwsenwsw",
        "sweneswneswneneenwnewenewwneswswnese",
        "swwesenesewenwneswnwwneseswwne",
        "enesenwswwswneneswsenwnewswseenwsese",
        "wnwnesenesenenwwnenwsewesewsesesew",
        "nenewswnwewswnenesenwnesewesw",
        "eneswnwswnwsenenwnwnwwseeswneewsenese",
        "neswnwewnwnwseenwseesewsenwsweewe",
        "wseweeenwnesenwwwswnew"
    )

    @Test
    fun `should start with white tiles`() {
        val tiling = FloorTiling()
        assertEquals(0, tiling.countBlackTiles())
    }

    @Test
    fun `should flip a tile`() {
        val tiling = FloorTiling()
        tiling.flipTiles(listOf("ese"))
        assertEquals(1, tiling.countBlackTiles())
    }

    @Test
    fun `should flip a tile back`() {
        val tiling = FloorTiling()
        val tile = "wsw"
        tiling.flipTiles(listOf(tile, tile, "w", "swnw"))
        assertEquals(0, tiling.countBlackTiles())
    }

    @Test
    fun `should flip tiles`() {
        val tiling = FloorTiling()
        tiling.flipTiles(input)
        assertEquals(10, tiling.countBlackTiles())
    }

    @Test
    fun `should flip actual tiles`() {
        val tiling = FloorTiling()
        tiling.flipTiles(readInputAsList())
        assertEquals(293, tiling.countBlackTiles())
    }

    @Test
    fun `should iterate`() {
        val tiling = FloorTiling()
        tiling.flipTiles(input)
        tiling.iterate()
        assertEquals(15, tiling.countBlackTiles())
        tiling.iterate()
        assertEquals(12, tiling.countBlackTiles())
    }

    @Test
    fun `should flip actual tiles and iterate`() {
        val tiling = FloorTiling()
        tiling.flipTiles(readInputAsList())
        repeat(100) {
            tiling.iterate()
        }
        assertEquals(3967, tiling.countBlackTiles())
    }
}