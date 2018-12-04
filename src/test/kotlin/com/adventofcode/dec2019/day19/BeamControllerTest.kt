package com.adventofcode.dec2019.day19

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class BeamControllerTest {

    @Test
    fun `should compute actually impacted points`() = runBlocking {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day19/input.txt").readLines()
        val controller = BeamController(program[0])
        assertEquals(171, controller.countAffectedPoints(0..49, 0..49))
    }

    @Test
    fun `should find beam width at 49`() = runBlocking {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day19/input.txt").readLines()
        val controller = BeamController(program[0])
        assertEquals(36..42, controller.findBeamWidth(49, controller::computePoint))
    }

    @Test
    fun `should find beam width at 100`() = runBlocking {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day19/input.txt").readLines()
        val controller = BeamController(program[0])
        assertEquals(916..1088, controller.findBeamWidth(1260, controller::computePoint))
    }

    @Test
    fun `should find closest 10 square in example`() = runBlocking {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day19/input.txt").readLines()
        val controller = BeamController(program[0])
        assertEquals(25 to 20, controller.findClosestSquare(10, 0..22) { x, y -> computePoint(x, y) })
    }

    @Test
    fun `should find closest 100 square`() = runBlocking {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day19/input.txt").readLines()
        val controller = BeamController(program[0])

        assertEquals(974 to 1242, controller.findClosestSquare(100, 1200..1400, controller::computePoint))
    }

    private fun computePoint(x: Int, y: Int): Int {
        return try {
            if (exampleBeam[y][x] == '.') 0 else 1
        } catch (e: Throwable) {
            0
        }
    }

    val exampleBeam = listOf(
            "#.......................................",
            ".#......................................",
            "..##....................................",
            "...###..................................",
            "....###.................................",
            ".....####...............................",
            "......#####.............................",
            "......######............................",
            ".......#######..........................",
            "........########........................",
            ".........#########......................",
            "..........#########.....................",
            "...........##########...................",
            "...........############.................",
            "............############................",
            ".............#############..............",
            "..............##############............",
            "...............###############..........",
            "................###############.........",
            "................#################.......",
            ".................########OOOOOOOOOO.....",
            "..................#######OOOOOOOOOO#....",
            "...................######OOOOOOOOOO###..",
            "....................#####OOOOOOOOOO#####",
            ".....................####OOOOOOOOOO#####",
            ".....................####OOOOOOOOOO#####",
            "......................###OOOOOOOOOO#####",
            ".......................##OOOOOOOOOO#####",
            "........................#OOOOOOOOOO#####",
            ".........................OOOOOOOOOO#####",
            "..........................##############",
            "..........................##############",
            "...........................#############",
            "............................############",
            ".............................###########")
}