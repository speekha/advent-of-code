package com.adventofcode.dec2021.day16

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BitsDecoderTest {

    @Test
    fun `should decode literal packet`() {
        val decoder = BitsDecoder()
        val packet = decoder.readPacket("D2FE28")
        assertEquals(LiteralValue(6, 4, 2021), packet)
    }

    @Test
    fun `should decode operator packet with length of subpackets`() {
        val decoder = BitsDecoder()
        val packet = decoder.readPacket("38006F45291200")
        assertEquals(
            Operator(
                1, 6, 0, listOf(
                    LiteralValue(6, 4, 10),
                    LiteralValue(2, 4, 20)
                )
            ), packet
        )
    }

    @Test
    fun `should decode operator packet with number of subpackets`() {
        val decoder = BitsDecoder()
        val packet = decoder.readPacket("EE00D40C823060")
        assertEquals(
            Operator(
                7, 3, 1,
                listOf(
                    LiteralValue(version = 2, type = 4, value = 1),
                    LiteralValue(version = 4, type = 4, value = 2),
                    LiteralValue(version = 1, type = 4, value = 3)
                )
            ), packet
        )
    }

    @Test
    fun `should sum version numbers`() {
        val decoder = BitsDecoder()
        val packet = decoder.readPacket("8A004A801A8002F478")
        assertEquals(16, packet.sumVersions())
    }

    @Test
    fun `should evaluate packets`() {
        val decoder = BitsDecoder()
        assertEquals(3, decoder.readPacket("C200B40A82").computeValue())
        assertEquals(54, decoder.readPacket("04005AC33890").computeValue())
        assertEquals(7, decoder.readPacket("880086C3E88112").computeValue())
        assertEquals(9, decoder.readPacket("CE00C43D881120").computeValue())
        assertEquals(1, decoder.readPacket("D8005AC2A8F0").computeValue())
        assertEquals(0, decoder.readPacket("F600BC2D8F").computeValue())
        assertEquals(0, decoder.readPacket("9C005AC2F8F0").computeValue())
        assertEquals(1, decoder.readPacket("9C0141080250320F1802104A08").computeValue())
    }

    @Test
    fun `should sum actual version numbers`() {
        val decoder = BitsDecoder()
        val packet = decoder.readPacket(actualInputList[0])
        assertEquals(929, packet.sumVersions())
    }

    @Test
    fun `should evaluate actual packets`() {
        val decoder = BitsDecoder()
        println(decoder.readPacket(actualInputList[0]).computeValue())
        assertEquals(911945136934, decoder.readPacket(actualInputList[0]).computeValue())
    }
}

