package com.adventofcode.dec2020.day05

class PlaneAnalyzer(input: List<String>) {

    private val parser = RowParser()

    private val seats = input.map { parser.parse(it) }

    fun findHighestSeat(): Int = seats.maxByOrNull { it.id }?.id ?: error("No data")

    fun findLowestSeat(): Int = seats.minByOrNull { it.id }?.id ?: error("No data")

    fun findMissingSeat(): Int {
        val min = findLowestSeat()
        val max = findHighestSeat()
        val ids = seats.map { it.id }
        return (min..max).first { !ids.contains(it) }
    }
}