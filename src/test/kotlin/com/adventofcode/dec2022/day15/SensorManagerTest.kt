package com.adventofcode.dec2022.day15

import com.adventofcode.actualInputList
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SensorManagerTest {

    val input = listOf(
        "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
        "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
        "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
        "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
        "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
        "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
        "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
        "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
        "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
        "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
        "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
        "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
        "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
        "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
    )

    @Test
    fun `should parse input`() {
        val manager = SensorManager(input.take(3))
        assertEquals(
            listOf(
                Sensor(Position(2, 18), Position(-2, 15)),
                Sensor(Position(9, 16), Position(10, 16)),
                Sensor(Position(13, 2), Position(15, 3)),
            ), manager.sensors
        )
    }

    @Test
    fun `should identify covered positions for a sensor`() {
        val manager = SensorManager(input)
        assertEquals(2.. 14, manager.sensors[6].getCoveredPositions(10))
        assertEquals(null, manager.sensors[0].getCoveredPositions(10))
    }

    @Test
    fun `should count covered positions`() {
        val manager = SensorManager(input)
        assertEquals(26, manager.countCoveredPositions(10))
    }

    @Test
    fun `should count actual covered positions`() {
        val manager = SensorManager(actualInputList)
        assertEquals(4861076, manager.countCoveredPositions(2000000))
    }

    @Test
    fun `should find tuning frequency`() {
        val manager = SensorManager(input)
        assertEquals(56000011, manager.findTuningFrequency(0..20))
    }

    @Test
    fun `should find actual tuning frequency`() {
        val manager = SensorManager(actualInputList)
        assertEquals(10649103160102, manager.findTuningFrequency(0..4000000))
    }
}