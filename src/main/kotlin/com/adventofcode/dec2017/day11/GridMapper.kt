package com.adventofcode.dec2017.day11

import java.io.File
import kotlin.math.min

class GridMapper {

    fun computeDistance(input: String): Int {
        val path = parsePath(input)
        return computeDistance(path)
    }

    fun findFurthestPoint(input: String): Int {
        val path = parsePath(input)
        return (0 .. path.size).map { computeDistance(path.subList(0, it)) }.max() ?: 0
    }

    private fun computeDistance(path: List<Direction>): Int {
        val axis = path.groupBy { it }
                .map { it.key to it.value.size }
                .associate { it }
                .toMutableMap()
        reduce(axis)
        return axis.asSequence().sumBy { it.value }
    }

    fun parsePath(input: String) = input.split(",").map { Direction.valueOf(it) }

    fun reduce(axis: MutableMap<Direction, Int>) {
        Direction.values().forEach {
            reduceOpposites(axis, it)
        }
        val keys = axis.keys.toList()
        keys.forEach {
            reduceThirdAxis(axis, it)
        }
    }

    private fun reduceOpposites(axis: MutableMap<Direction, Int>, it: Direction) {
        val value = axis.getOrDefault(it, 0)
        val opposite = axis.getOrDefault(it.opposite(), 0)
        if (opposite in 1..value) {
            axis[it] = value - opposite
            axis.remove(it.opposite())
        }
    }

    private fun reduceThirdAxis(axis: MutableMap<Direction, Int>, it: Direction) {
        val value = axis[it] ?: 0
        val combine = axis.getOrDefault(it.rotate(2), 0)
        if (value > 0 && combine > 0) {
            val min = min(value, combine)
            axis[it] = value - min
            axis[it.rotate(2)] = combine - min
            axis[it.rotate(1)] = axis.getOrDefault(it.rotate(1), 0) + min
        }
    }
}