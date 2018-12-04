package com.adventofcode.dec2019.day03

import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class WireTracerTest {

    @Test
    fun `should load wire map`() {
        val input = "R8,U5,L5,D3\nU7,R6,D4,L4"
        val mapper = WireTracer(input.split("\n"))
        assertEquals(2, mapper.wires.size)
        assertEquals(22, mapper.wires[0].size)
    }

    @Test
    fun `should find closest intersection`() {
        val input = "R8,U5,L5,D3\nU7,R6,D4,L4"
        val mapper = WireTracer(input.split("\n"))
        assertEquals(Position(3, -3), mapper.findClosestIntersection())
        assertEquals(6, mapper.findDistanceToClosestIntersection())
    }

    @Test
    fun `should find actual closest intersection`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day03/input.txt").readLines()
        val runner = WireTracer(input)
        assertEquals(529, runner.findDistanceToClosestIntersection())
    }


    @Test
    fun `should find shortest path to intersection`() {
        val input = "R8,U5,L5,D3\nU7,R6,D4,L4"
        val mapper = WireTracer(input.split("\n"))
        assertEquals(30, mapper.findshortestPathToIntersection())
    }

    @Test
    fun `should find actual shortest path to intersection`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day03/input.txt").readLines()
        val runner = WireTracer(input)
        assertEquals(20386, runner.findshortestPathToIntersection())
    }

}