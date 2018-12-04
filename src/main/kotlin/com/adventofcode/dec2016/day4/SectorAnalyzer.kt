package com.adventofcode.dec2016.day4

import java.io.File

class SectorAnalyzer {

    fun isARealRoom(roomId: String) = getChecksum(roomId) == computeChecksum(roomId)

    fun getRoomName(roomId: String) = roomId.substring(0, roomId.lastIndexOf('-'))

    fun getChecksum(roomId: String) = roomId.substringAfter('[').substringBefore(']')

    fun computeChecksum(roomId: String) = getRoomName(roomId)
            .replace("-", "")
            .associate { char -> char to roomId.count { it == char } }
            .asIterable()
            .sortedWith(Comparator { o1, o2 ->
                if (o1.value == o2.value)
                    o1.key - o2.key
                else
                    o2.value - o1.value
            })
            .take(5)
            .joinToString("") { it.key.toString() }

    fun addRealRoomSectors(input: List<String>) = input.sumBy {
        if (isARealRoom(it)) getSectorId(it).toInt() else 0
    }

    fun getSectorId(roomId: String) =
            roomId.substringAfterLast('-')
                    .substringBefore('[')

    fun decipherRoomName(roomName: String, sector: Int) = roomName.map {
        when (it) {
            '-' -> ' '
            else -> ((it - 'a' + sector) % 26 + 'a'.toInt()).toChar()
        }
    }.joinToString("")

    fun filter(input: List<String>, needle: String) = input
            .filter {
                isARealRoom(it) && decipherRoomName(getRoomName(it), getSectorId(it).toInt()).contains(needle)
            }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day4/input.txt").readLines()
    println("Testing : ${input.size}")
    with(SectorAnalyzer()) {
        println("Real rooms: ${addRealRoomSectors(input)}")
        val room = filter(input, "north").first()
        println("North: ${room} -> ${decipherRoomName(getRoomName(room), getSectorId(room).toInt())}")
    }
}


