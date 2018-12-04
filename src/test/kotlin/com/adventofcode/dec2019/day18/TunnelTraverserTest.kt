package com.adventofcode.dec2019.day18

import com.adventofcode.positioning.Position
import com.adventofcode.dec2019.day18.Cell.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class TunnelTraverserTest {

    val input1 = listOf("#########",
            "#b.A.@.a#",
            "#########")

    val input2 = listOf("########################",
            "#f.D.E.e.C.b.A.@.a.B.c.#",
            "######################.#",
            "#d.....................#",
            "########################")

    val input3 = listOf("#################",
            "#i.G..c...e..H.p#",
            "########.########",
            "#j.A..b...f..D.o#",
            "########@########",
            "#k.E..a...g..B.n#",
            "########.########",
            "#l.F..d...h..C.m#",
            "#################")

    val input4 = listOf(
            "#######",
            "#a.#Cd#",
            "##...##",
            "##.@.##",
            "##...##",
            "#cB#Ab#",
            "#######")

    @Test
    fun `should map tunnels`() {
        val traverser = TunnelTraverser(input1)
        assertEquals(
                listOf(
                        listOf(Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall),
                        listOf(Wall, Key('b'), Empty, Door('A'), Empty, Entrance, Empty, Key('a'), Wall),
                        listOf(Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall)),
                traverser.map.map { it.map { it } })
    }

    @Test
    fun `should start at entrance point`() {
        val traverser = TunnelTraverser(input1)
        assertEquals(Position(5, 1), traverser.entrances[0])
    }

    @Test
    fun `should list all keys`() {
        val traverser = TunnelTraverser(input1)
        assertEquals(mapOf('b' to Position(1, 1), 'a' to Position(7, 1)), traverser.keys)
    }

    @Test
    fun `should list all distances`() {
        val traverser = TunnelTraverser(input1)
        val explorer = Explorer(traverser.map, traverser.entrances[0], traverser.keys)
        explorer.listDistances()
        assertEquals(mapOf(
                Entrance to mapOf('a' to Link(2, listOf()),
                        'b' to Link(4, listOf('a'))),
                Key('a') to mapOf('b' to Link(6, listOf('a'))),
                Key('b') to mapOf('a' to Link(6, listOf('a')))),
                explorer.distances)
    }

    @Test
    fun `should collect all keys in first case`() {
        val traverser = TunnelTraverser(input1)
        assertEquals(8, traverser.collectAllKeys())
    }

    @Test
    fun `should collect all keys in second case`() {
        val traverser = TunnelTraverser(input2)
        assertEquals(86, traverser.collectAllKeys())
    }

    @Test
    fun `should collect all keys in third case`() {
        val traverser = TunnelTraverser(input3)
        assertEquals(136, traverser.collectAllKeys())
    }
    
    @Test
    fun `should collect all keys from actual maze`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day18/input.txt").readLines()
        val traverser = TunnelTraverser(input)
        assertEquals(5964, traverser.collectAllKeys())
    }

    @Test
    fun `should collect all keys in four areas`() {
        val traverser = TunnelTraverser(input4)
        traverser.divideAndConquer()
        assertEquals(8, traverser.collectAllKeys())
    }


    @Test
    fun `should collect all keys from actual maze by splitting it`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day18/input.txt").readLines()
        val traverser = TunnelTraverser(input)
        traverser.divideAndConquer()
        assertEquals(1996, traverser.collectAllKeys())
    }
}