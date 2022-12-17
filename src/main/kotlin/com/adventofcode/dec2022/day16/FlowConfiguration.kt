package com.adventofcode.dec2022.day16

data class FlowConfiguration(
    val position: Valve,
    val openedValves: List<Valve> = emptyList(),
    val flowRate: Int = 0,
    val pressureReleased: Int = 0,
    val timeLeft: Int = 0
) : Comparable<FlowConfiguration> {
    fun open(valves: Map<String, Valve>): FlowConfiguration {
        return FlowConfiguration(
            position,
            openedValves + position,
            flowRate + position.flow,
            pressureReleased = pressureReleased + flowRate,
            timeLeft -1
        )
    }

    fun moveTo(valve: Valve) = FlowConfiguration(valve, openedValves, flowRate, pressureReleased + flowRate, timeLeft - 1)

    override fun compareTo(other: FlowConfiguration): Int = flowRate.compareTo(other.flowRate)
}