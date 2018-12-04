package com.adventofcode.dec2019.day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class ImageProcessorTest {

    @Test
    fun `should load image`() {
        val image = "123456789012"
        val decoder = ImageProcessor(image, 3, 2)
        assertEquals(2, decoder.layers.size)
    }

    @Test
    fun `should find requested layer`() {
        val image = "123456789012"
        val decoder = ImageProcessor(image, 3, 2)
        assertEquals("123456", decoder.findTestLayer())
    }

    @Test
    fun `should calculate checksum`() {
        val image = "123456789012"
        val decoder = ImageProcessor(image, 3, 2)
        assertEquals(1, decoder.calculateChecksum())
    }

    @Test
    fun `should calculate checksum on actual image`() {
        val image = File("src/main/kotlin/com/adventofcode/dec2019/day08/input.txt").readLines()
        val decoder = ImageProcessor(image[0], 25, 6)
        assertEquals(1064, decoder.calculateChecksum())
    }

    @Test
    fun `should decode image`() {
        val image = "0222112222120000"
        val decoder = ImageProcessor(image, 2, 2)
        assertEquals(" #\n# ", decoder.render())
    }

    @Test
    fun `should render actual image`() {
        val image = File("src/main/kotlin/com/adventofcode/dec2019/day08/input.txt").readLines()
        val decoder = ImageProcessor(image[0], 25, 6)
        println(decoder.render())
    }
}