package com.adventofcode.dec2021.day04

data class BingoCard(
    val numbers: List<Int>
) {

    private val checked = Array(5) {
        BooleanArray(5)
    }

    fun getScore(lastDraw: Int): Int = lastDraw * numbers.withIndex().sumOf { (index, value) ->
        if (checked[index / 5][index % 5]) 0 else value
    }

    fun isWinning(): Boolean = (0..4).any { i ->
        val winningRow = checked[i].all { it }
        val winningColumn = checked.map { it[i] }.all { it }
        winningRow || winningColumn
    }

    fun mark(value: Int) {
        val index = numbers.indexOf(value)
        if (index >= 0) {
            checked[index / 5][index % 5] = true
        }
    }
}