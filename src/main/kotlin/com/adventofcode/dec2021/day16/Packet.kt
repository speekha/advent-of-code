package com.adventofcode.dec2021.day16

import kotlin.math.max
import kotlin.math.min

interface Packet {
    val version: Int
    val type: Int
    fun computeValue(): Long
    fun sumVersions(): Int
}

data class LiteralValue(
    override val version: Int,
    override val type: Int,
    val value: Long
) : Packet {
    override fun sumVersions(): Int = version
    override fun computeValue(): Long = value
}

data class Operator(
    override val version: Int,
    override val type: Int,
    val lengthType: Int,
    val subpackets: List<Packet>
) : Packet {
    override fun sumVersions(): Int = version + subpackets.sumOf { it.sumVersions() }
    override fun computeValue(): Long =
        when (type) {
            0 -> subpackets.sumOf { it.computeValue() }
            1 -> subpackets.fold(1) { acc, packet -> acc * packet.computeValue() }
            2 -> subpackets.minOf { it.computeValue() }
            3 -> subpackets.maxOf { it.computeValue() }
            5 -> if (subpackets[0].computeValue() > subpackets[1].computeValue()) 1 else 0
            6 -> if (subpackets[0].computeValue() < subpackets[1].computeValue()) 1 else 0
            7 -> if (subpackets[0].computeValue() == subpackets[1].computeValue()) 1 else 0
            else -> 0
        }
}
