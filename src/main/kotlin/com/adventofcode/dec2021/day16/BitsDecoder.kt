package com.adventofcode.dec2021.day16

class BitsDecoder {

    fun readPacket(input: String): Packet {
        val stream = BitsStream(input)
        return stream.readPacket()
    }

    private fun BitsStream.readPacket(): Packet {
        val version = readValue(3)
        val type = readValue(3)
        return when (type) {
            4 -> readLiteralValue(version)
            else -> readOperator(version, type)
        }
    }

    private fun BitsStream.readLiteralValue(version: Int): LiteralValue {
        var data = 0L
        var keepReading: Boolean
        do {
            keepReading = readBit() == 1
            data = 16 * data + readValue(4)
        } while (keepReading)
        return LiteralValue(version, 4, data)
    }

    private fun BitsStream.readOperator(version: Int, type: Int): Packet {
        val lengthType = readBit()
        val subpackets = mutableListOf<Packet>()
        if (lengthType == 0) {
            val length = readValue(15)
            val packetStart = index
            while (index < packetStart + length) {
                subpackets += readPacket()
            }
        } else {
            val count = readValue(11)
            while (subpackets.size < count) {
                subpackets += readPacket()
            }
        }
        return Operator(version, type, lengthType, subpackets)
    }
}
