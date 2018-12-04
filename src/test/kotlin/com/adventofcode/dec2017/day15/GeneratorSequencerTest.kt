package com.adventofcode.dec2017.day15

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GeneratorSequencerTest {

    val inputA = listOf(
            "1092455",
            "1181022009",
            "245556042",
            "1744312007",
            "1352636452")

    val inputB = listOf(
            "430625591",
            "1233683848",
            "1431495498",
            "137874439",
            "285222916")

    val binaryInputA = listOf(
            "00000000000100001010101101100111",
            "01000110011001001111011100111001",
            "00001110101000101110001101001010",
            "01100111111110000001011011000111",
            "01010000100111111001100000100100")

    val binaryInputB = listOf(
            "00011001101010101101001100110111",
            "01001001100010001000010110001000",
            "01010101010100101110001101001010",
            "00001000001101111100110000000111",
            "00010001000000000010100000000100")


    @Test
    fun `generator A should match output`() {
        val sequencerA = GeneratorSequencer(65, 16807)
        inputA.forEach {
            Assertions.assertEquals(it.toLong(), sequencerA.computeNextValue())
        }
    }

    @Test
    fun `generator A should match binary output`() {
        val sequencerA = GeneratorSequencer(65, 16807)
        binaryInputA.forEach {
            Assertions.assertEquals(it, sequencerA.computeNextBinaryValue())
        }
    }

    @Test
    fun `generator B should match output`() {
        val sequencerB = GeneratorSequencer(8921, 48271)
        inputB.forEach {
            Assertions.assertEquals(it.toLong(), sequencerB.computeNextValue())
        }
    }

    @Test
    fun `generator B should match binary output`() {
        val sequencerB = GeneratorSequencer(8921, 48271)
        binaryInputB.forEach {
            Assertions.assertEquals(it, sequencerB.computeNextBinaryValue())
        }
    }

    val binaryInputA4 = listOf(
            "01010000100111111001100000100100",
            "01110110101111001011111010110000",
            "00011111101000111101010001100100",
            "01110110000001001010100110110000",
            "00101100001000001001111001011000")


    val binaryInputB8 = listOf(
            "01001001100010001000010110001000",
            "00110011011010001111010010000000",
            "01000101001000001110100001111000",
            "01100000010100110001010101001000",
            "00011000100100101011101101010000")


    @Test
    fun `new generator A should match binary output`() {
        val sequencerA = GeneratorSequencer(65, 16807, 2)
        binaryInputA4.forEach {
            Assertions.assertEquals(it, sequencerA.computeNextBinaryValue())
        }
    }

    @Test
    fun `new generator B should match binary output`() {
        val sequencerB = GeneratorSequencer(8921, 48271, 3)
        binaryInputB8.forEach {
            Assertions.assertEquals(it, sequencerB.computeNextBinaryValue())
        }
    }
}
