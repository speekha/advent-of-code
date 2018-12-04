package com.adventofcode.dec2019.day16

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.math.abs

@Disabled
class FFTProcessorTest {

    @Test
    fun `should process simple input`() {
        val inputs = listOf("12345678", "48226158", "34040438", "03415518", "01029498")
        val processor = FFTProcessor(1, 8)
        inputs.dropLast(1).indices.forEach {
            assertEquals(inputs[it + 1], processor.process(inputs[it]))
        }
    }

    @Test
    fun `should compute 100 phases`() {
        val input = listOf("80871224585914546619083218645595", "19617804207202209144916044189917", "69317163492948606335995924319873")
        val output = listOf("24176176", "73745418", "52432133")
        val processor = FFTProcessor(1, 8)
        input.zip(output) { inS, outS ->
            assertEquals(outS, processor.calculate(inS, 100))
        }
    }

    @Test
    fun `should compute 100 phases with actual data`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day16/input.txt").readLines()
        val processor = FFTProcessor(1, 8)
        assertEquals("49254779", processor.calculate(input[0], 100))
    }


    @Test
    fun `should compute repeated input with offset`() {
        val input = listOf("03036732577212944063491565474664", "02935109699940807407585447034323", "03081770884921959731165446850517")
        val output = listOf("84462026", "78725270", "53553731")
        val processor = FFTProcessor(10000, 8)
        input.zip(output) { inS, outS ->
            assertEquals(outS, processor.calculateWithOffset(inS, 100))
        }
    }

    @Test
    fun `should compute repeated input with actual data`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day16/input.txt").readLines()
        val processor = FFTProcessor(10000, 8)
        assertEquals("55078585", processor.calculateWithOffset(input[0], 100))
    }
}