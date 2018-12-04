package com.adventofcode.dec2017.day19

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class PacketRouterTest {

    val input = listOf(
            "     |         ",
            "     |  +--+   ",
            "     A  |  C   ",
            " F---|----E|--+",
            "     |  |  |  D",
            "     +B-+  +--+")


    @Test
    fun `should start at the top`() {
        val router = PacketRouter(input)
        Assertions.assertEquals(Position(5, 0), router.findStartingPoint())
    }

    @Test
    fun `should go right`() {
        val router = PacketRouter(input)
        Assertions.assertEquals(Direction.RIGHT, router.turn(Position(5, 5), Direction.DOWN))
    }

    @Test
    fun `should go up`() {
        val router = PacketRouter(input)
        Assertions.assertEquals(Direction.UP, router.turn(Position(8, 5), Direction.RIGHT))
    }

    @Test
    fun `should go down`() {
        val router = PacketRouter(input)
        Assertions.assertEquals(Direction.DOWN, router.turn(Position(11, 1), Direction.RIGHT))
    }

    @Test
    fun `should go left`() {
        val router = PacketRouter(input)
        Assertions.assertEquals(Direction.LEFT, router.turn(Position(14, 3), Direction.UP))
    }

    @Test
    fun `should navigate graph`() {
        val router = PacketRouter(input)
        Assertions.assertEquals("ABCDEF" to 38, router.traverse())
    }

    @Test
    fun `should navigate real graph`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day19/input.txt").readLines()
        val router = PacketRouter(input)
        Assertions.assertEquals("QPRYCIOLU" to 16162, router.traverse())
    }
}