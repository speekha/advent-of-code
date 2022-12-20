package com.adventofcode.dec2022.day16

data class FlowConfiguration(
    val position: Valve,
    val openedValves: List<Valve> = emptyList(),
    val pressureReleased: Int = 0,
    val timeLeft: Int = 0
) : Comparable<FlowConfiguration> {

    var path: String = ""

    private val comparator = compareBy<FlowConfiguration> {
        it.openedValves.count()
    }.thenBy {
        it.pressureReleased
    }
    override fun compareTo(other: FlowConfiguration): Int = comparator.compare(this, other)

    fun key():String = "${position.name}-${openedValves.joinToString { it.name }}"
}