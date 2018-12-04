package com.adventofcode.dec2018.day4

import java.io.File

class GuardStatistics(input: List<String>) {
    val records: List<GuardRecord> = input.toRecords()

    private fun List<String>.toRecords(): List<GuardRecord> {
        val list = sorted()
        val result = mutableListOf<GuardRecord>()
        var guard: String? = null
        list.forEach {
            val date = it.substring(0..it.indexOf("]"))
            val tokens = it.substring(date.length + 1).split(" ")
            val event = when (tokens[0]) {
                "Guard" -> Event.SHIFT
                "falls" -> Event.SLEEP
                "wakes" -> Event.WAKE_UP
                else -> error("Unknown event")
            }
            val id = if (event == Event.SHIFT) tokens[1] else guard ?: error("No guard")
            guard = id
            result.add(GuardRecord(date, id, event))
        }
        return result
    }

    fun findGuardWithMostSleep(): String {
        return records
                .filter { it.event != Event.SHIFT }
                .groupBy { it.id }
                .mapValues { entry ->
                    entry.value
                            .chunked(2)
                            .map { it[1].date.minutes - it[0].date.minutes }
                            .sum()
                }
                .maxByOrNull { it.value }
                ?.key ?: error("No max available")
    }

    fun findMostSleepedMinute(id: String): Int {
        val stats = records
                .filter { it.id == id && it.event != Event.SHIFT }
                .map { it.date.minutes }
                .chunked(2)
                .map { it[0] until it[1] }
        val minutes = Array(60) { minute -> stats.count { sleepRange -> minute in sleepRange } }
        return minutes.indexOf(minutes.maxByOrNull { it } ?: error("No max"))
    }

    fun findBestCombination(): Int {
        val guard = findGuardWithMostSleep()
        val minute = findMostSleepedMinute(guard)
        return guard.toId() * minute
    }

    fun findBestGuardMinuteCombination(): Int {
        val mappedData =  records
                .filter { it.event != Event.SHIFT }
                .groupBy { it.id }
                .mapValues { entry ->
                    val ranges = entry.value
                            .chunked(2)
                            .map { it[0].date.minutes until it[1].date.minutes }
                    Array(60) { minute -> ranges.count { sleepRange -> minute in sleepRange } }
                }
        val bestCombo = mappedData.maxByOrNull { it.value.maxOrNull() ?: 0 } ?: error("No match")
        val bestMinute = (mappedData[bestCombo.key] ?: error("Guard record")).indexOf(bestCombo.value.maxOrNull())
        return bestCombo.key.toId() * bestMinute
    }

    private val String.minutes: Int
        get() = substring(length - 3, length - 1).toInt()

    private fun String.toId(): Int = substring(1).toInt()
}


fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day4/input.txt").readLines()
    val stats = GuardStatistics(input)
    println("Best combination: ${stats.findBestCombination()}")
    println("Best combination: ${stats.findBestGuardMinuteCombination()}")

}
