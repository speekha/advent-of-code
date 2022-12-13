package com.adventofcode.dec2022.day13

class PacketDecoder {
    fun listOrderedPairs(input: String): List<Int> = input.split("\n\n")
        .map { Pair(it.split("\n")) }
        .withIndex()
        .filter { it.value.isOrdered() }
        .map { it.index + 1 }

    fun findDecoderPackets(input: String, vararg decoders: Int): List<Int> {
        val packets = input.split("\n")
            .filter { it.isNotEmpty() }
            .map { PairParser(it).parse() }
        val decoderPackets = decoders.map { ListElement(ListElement(IntElement(it))) }
        val fullList = (packets + decoderPackets).sorted()
        return decoderPackets.map { fullList.indexOf(it) + 1 }
    }

}
