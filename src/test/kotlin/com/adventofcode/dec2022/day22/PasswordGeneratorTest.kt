package com.adventofcode.dec2022.day22

import com.adventofcode.actualInput
import com.adventofcode.actualInputList
import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordGeneratorTest {

    val input = listOf(
        "        ...#",
        "        .#..",
        "        #...",
        "        ....",
        "...#.......#",
        "........#...",
        "..#....#....",
        "..........#.",
        "        ...#....",
        "        .....#..",
        "        .#......",
        "        ......#.",
        "",
        "10R5L5R10L4R5L5"
    )

    @Test
    fun `should parse input`() {
        val generator = PasswordGenerator(input.dropLast(2))
        assertEquals(Position(8, 0), generator.position)
        assertEquals(Direction.RIGHT, generator.direction)
    }

    @Test
    fun `should parse moves`() {
        val generator = PasswordGenerator(input.dropLast(2))
        generator.move(1)
        assertEquals(Position(9, 0), generator.position)
    }

    @Test
    fun `should stop at walls`() {
        val generator = PasswordGenerator(input.dropLast(2))
        generator.move(4)
        assertEquals(Position(10, 0), generator.position)
    }

    @Test
    fun `should turn right`() {
        val generator = PasswordGenerator(input.dropLast(2))
        generator.move(4)
        generator.turnRight()
        assertEquals(Position(10, 0), generator.position)
        assertEquals(Direction.DOWN, generator.direction)
    }

    @Test
    fun `should turn left`() {
        val generator = PasswordGenerator(input.dropLast(2))
        generator.move(4)
        generator.turnRight()
        generator.move(1)
        generator.turnLeft()
        assertEquals(Position(10, 1), generator.position)
        assertEquals(Direction.RIGHT, generator.direction)
    }

    @Test
    fun `should wrap around`() {
        val generator = PasswordGenerator(input.dropLast(2))
        generator.move(4)
        generator.turnRight()
        generator.move(1)
        generator.turnLeft()
        generator.move(2)
        assertEquals(Position(8, 1), generator.position)
        assertEquals(Direction.RIGHT, generator.direction)
    }

    @Test
    fun `should move sequence`() {
        val generator = PasswordGenerator(input.dropLast(2))
        val sequence = input.last()
        generator.move(sequence)
        assertEquals(Position(7, 5), generator.position)
        assertEquals(Direction.RIGHT, generator.direction)
    }
    @Test
    fun `should compute password`() {
        val generator = PasswordGenerator(input.dropLast(2))
        val sequence = input.last()
        generator.move(sequence)
        assertEquals(6032, generator.computePassword())
    }
    @Test
    fun `should compute actual password`() {
        val input = actualInputList
        val generator = PasswordGenerator(input.dropLast(2))
        val sequence = input.last()
        generator.move(sequence)
        assertEquals(27436, generator.computePassword())
    }
}