package com.adventofcode.dec2022.day13

import com.adventofcode.actualInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PacketDecoderTest {

    val input = "[1,1,3,1,1]\n" +
            "[1,1,5,1,1]\n" +
            "\n" +
            "[[1],[2,3,4]]\n" +
            "[[1],4]\n" +
            "\n" +
            "[9]\n" +
            "[[8,7,6]]\n" +
            "\n" +
            "[[4,4],4,4]\n" +
            "[[4,4],4,4,4]\n" +
            "\n" +
            "[7,7,7,7]\n" +
            "[7,7,7]\n" +
            "\n" +
            "[]\n" +
            "[3]\n" +
            "\n" +
            "[[[]]]\n" +
            "[[]]\n" +
            "\n" +
            "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
            "[1,[2,[3,[4,[5,6,0]]]],8,9]\n"

    @Test
    fun `should decode packets`() {
        val input = "[1,1,3,[1,1]]\n[1,[1,5],1,1]"
        val pair = input.split("\n\n").map { Pair(it.split("\n")) }[0]
        assertEquals(
            ListElement(
                IntElement(1), IntElement(1), IntElement(3), ListElement(
                    IntElement(1), IntElement(1)
                )
            ), pair.left
        )
        assertEquals(
            ListElement(
                IntElement(1),
                ListElement(IntElement(1), IntElement(5)),
                IntElement(1),
                IntElement(1)
            ), pair.right
        )
        assertEquals(ListElement(ListElement()), PairParser("[[]]").parse())
        assertEquals(ListElement(), PairParser("[]").parse())
    }

    @Test
    fun `should find unordered pairs`() {
        val decoder = PacketDecoder()
        assertEquals(listOf(1, 2, 4, 6), decoder.listOrderedPairs(input))
        assertEquals(13, decoder.listOrderedPairs(input).sum())
    }

    @Test
    fun `should find actual unordered pairs`() {
        val decoder = PacketDecoder()
        assertEquals(6568, decoder.listOrderedPairs(actualInput).sum())
    }

    @Test
    fun `should find decoder packets`() {
        val decoder = PacketDecoder()
        assertEquals(listOf(10, 14), decoder.findDecoderPackets(input, 2, 6))
    }

    @Test
    fun `should find actual decoder packets`() {
        val decoder = PacketDecoder()
        val indexes = decoder.findDecoderPackets(actualInput, 2, 6)
        assertEquals(19493, indexes[0] * indexes[1])
    }
}