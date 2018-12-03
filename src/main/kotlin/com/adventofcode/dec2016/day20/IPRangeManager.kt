package com.adventofcode.dec2016.day20

import java.io.File

class IPRangeManager {

    val blockedIP = mutableListOf<LongRange>()

    fun addBlackListRanges(list: List<LongRange>) {
        val ranges = list.sortedBy { it.first }
        var range: LongRange? = null
        ranges.forEach {
            range.let { localRange ->
                when {
                    localRange == null -> range = it
                    it.first <= localRange.last + 1 -> if (it.last > localRange.last) {
                        range = localRange.first..it.last
                    }
                    else -> {
                        blockedIP += localRange
                        range = it
                    }
                }
            }
        }
        range?.let {
            blockedIP += it
        }
    }

    fun getBlackList(): List<LongRange> = blockedIP

    fun getLowestWhiteIP(): Long = if (blockedIP[0].first > 0) 0 else blockedIP[0].last + 1

    fun addBlackListRange(list: List<String>) {
        addBlackListRanges(list.map {
            val (first, last) = it.split("-")
            first.toLong()..last.toLong()
        })
    }

    fun countBlockedIP(): Long {
        var total = 0.toLong()
        blockedIP.forEach { total += it.last - it.first + 1 }
        return total
    }

    fun countAllowedIP(): Long {
        return 4294967296 - countBlockedIP()
    }
}

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day20/input.txt").readLines()
    val ipManager = IPRangeManager()
    ipManager.addBlackListRange(input)
    println("First non-blocked IP: ${ipManager.getLowestWhiteIP()}")
    println("Allowed IP: ${ipManager.countAllowedIP()}")
}
