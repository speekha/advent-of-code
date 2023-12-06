package com.adventofcode.dec2023.day06

class BoatRace {
    fun countWinOptions(time: Long, record: Long): Int = (1L until time).count { i ->
        i * (time - i) > record
    }

    fun computeRaceStrategy(input: List<String>): Long {
        val times = input[0].dropWhile { it !in '0'..'9' }.split(" +".toRegex()).map { it.toLong() }
        val records = input[1].dropWhile { it !in '0'..'9' }.split(" +".toRegex()).map { it.toLong() }
        return times.indices.fold(1) { acc, i ->
            acc * countWinOptions(times[i], records[i])
        }
    }

    fun computeIncreasedRaceStrategy(input: List<String>): Long {
        val time = input[0].filter { it in '0'..'9' }.toLong()
        val record = input[1].filter { it in '0'..'9' }.toLong()
        return  countWinOptions(time, record).toLong()
    }

}
