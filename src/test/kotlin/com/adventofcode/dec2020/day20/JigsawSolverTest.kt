package com.adventofcode.dec2020.day20

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JigsawSolverTest {

    val input = readInputAsList("testData.txt")

    @Test
    fun `should load tiles`() {
        val solver = JigsawSolver(input)
        assertEquals(9, solver.tiles.size)
//        assertEquals(Tile(2311, intArrayOf(210, 89, 231, 498), intArrayOf(300, 616, 924, 318)), solver.tiles[2311])
    }

    @Test
    fun `should find neighbor`() {
        val solver = JigsawSolver(input)
        assertEquals(listOf(1489, 2311, 2473, 2729), solver.findNeighbors(1427).sorted())
    }

    @Test
    fun `should find corners`() {
        val solver = JigsawSolver(input)
        assertEquals(listOf(1171, 1951, 2971, 3079), solver.corners.map { it.id }.sorted())
        assertEquals(20899048083289, solver.corners.fold(1L) { acc, i -> acc * i.id })
    }

    @Test
    fun `should find actual corners`() {
        val solver = JigsawSolver(readInputAsList())
        assertEquals(83775126454273, solver.corners.fold(1L) { acc, i -> acc * i.id })
    }

    @Test
    fun `should reconstitute image`() {
        val solver = JigsawSolver(input)
        assertEquals(listOf(
            listOf(1951, 2311, 3079),
            listOf(2729, 1427, 2473),
            listOf(2971, 1489, 1171)
        ), solver.assembleJigsaw().map { it.map { it.id } })
    }

    @Test
    fun `should merge maps`() {
        val merged = listOf(
            ".#.#..#.##...#.##..#####",
            "###....#.#....#..#......",
            "##.##.###.#.#..######...",
            "###.#####...#.#####.#..#",
            "##.#....#.##.####...#.##",
            "...########.#....#####.#",
            "....#..#...##..#.#.###..",
            ".####...#..#.....#......",
            "#..#.##..#..###.#.##....",
            "#.####..#.####.#.#.###..",
            "###.#.#...#.######.#..##",
            "#.####....##..########.#",
            "##..##.#...#...#.#.#.#..",
            "...#..#..#.#.##..###.###",
            ".#.#....#.##.#...###.##.",
            "###.#...#..#.##.######..",
            ".#.#.###.##.##.#..#.##..",
            ".####.###.#...###.#..#.#",
            "..#.#..#..#.#.#.####.###",
            "#..####...#.#.#.###.###.",
            "#####..#####...###....##",
            "#.##..#..#...#..####...#",
            ".#.###..##..##..####.##.",
            "...###...##...#...#..###"
        )
        val solver = JigsawSolver(input)
        assertEquals(merged, solver.buildImage())
    }

    @Test
    fun `should count sea monsters`() {
        val solver = JigsawSolver(input)
        assertEquals(2, solver.countSeaMonsters().second)
    }

    @Test
    fun `should count actual sea monsters`() {
        val solver = JigsawSolver(readInputAsList())
        assertEquals(35, solver.countSeaMonsters().second)
    }

    @Test
    fun `should compute sea roughness`() {
        val solver = JigsawSolver(input)
        assertEquals(273, solver.computeRoughness())
    }

    @Test
    fun `should compute actual sea roughness`() {
        val solver = JigsawSolver(readInputAsList())
        // Between 1800 and 2068, not 2053, not 2055
        val result = solver.computeRoughness()
        println("Guess = $result")
        assertEquals(1993, solver.computeRoughness())
    }

    @Test
    fun `should flip data`() {
        val data = listOf("123", "456")
        assertEquals(listOf("321", "654"), data.flip())
    }

    @Test
    fun `should rotate data`() {
        val data = listOf(
            "123",
            "456"
        )
        assertEquals(
            listOf(
                "36",
                "25",
                "14"
            ), data.rotateLeft()
        )
    }
}