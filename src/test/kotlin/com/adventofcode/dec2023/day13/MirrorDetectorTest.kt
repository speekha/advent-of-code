package com.adventofcode.dec2023.day13

import com.adventofcode.actualInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MirrorDetectorTest {
    val input =
        "#.##..##.\n" +
                "..#.##.#.\n" +
                "##......#\n" +
                "##......#\n" +
                "..#.##.#.\n" +
                "..##..##.\n" +
                "#.#.##.#.\n" +
                "\n" +
                "#...##..#\n" +
                "#....#..#\n" +
                "..##..###\n" +
                "#####.##.\n" +
                "#####.##.\n" +
                "..##..###\n" +
                "#....#..#"


    @Test
    fun `should load maps`() {
        val detector = MirrorDetector(input)
        assertEquals(2, detector.patterns.size)
    }

    @Test
    fun `should find horizontal reflections`() {
        val detector = MirrorDetector(input)
        assertEquals(0, detector.detectHorizontalReflector(detector.patterns[0]))
        assertEquals(4, detector.detectHorizontalReflector(detector.patterns[1]))
    }

    @Test
    fun `should find vertical reflections`() {
        val detector = MirrorDetector(input)
        assertEquals(5, detector.detectHorizontalReflector(flip(detector.patterns[0])))
        assertEquals(0, detector.detectHorizontalReflector(flip(detector.patterns[1])))
    }

    @Test
    fun `should find all reflections`() {
        val detector = MirrorDetector(input)
        assertEquals(405, detector.detectReflectors())
    }

    @Test
    fun `should find all with smudges reflections`() {
        val detector = MirrorDetector(input)
        assertEquals(400, detector.detectReflectorsWithSmudges())
    }

    @Test
    fun `should find all actual reflections`() {
        val detector = MirrorDetector(actualInput)
        assertEquals(34100, detector.detectReflectors())
    }

    @Test
    fun `should find all actual reflections with smudges`() {
        val detector = MirrorDetector(actualInput)
        assertEquals(33106, detector.detectReflectorsWithSmudges())
    }
}