package com.adventofcode.dec2022.day16

data class FlowConfiguration(
    val positions: List<Valve>,
    val openedValves: List<Valve> = emptyList(),
    val pressureReleased: Int = 0,
    val timeLeft: List<Int>
) : Comparable<FlowConfiguration> {

    var path: String = ""

    private val comparator = compareBy<FlowConfiguration> {
        it.openedValves.count()
    }.thenBy {
        it.pressureReleased
    }
    override fun compareTo(other: FlowConfiguration): Int = comparator.compare(this, other)

    fun key():String {
        val pos = positions.sortedBy { it.name }.joinToString{ it.name }
        return "$pos-${openedValves.joinToString { it.name }}"
    }
}