package com.adventofcode.dec2017.day24

typealias Component = Pair<Int, Int>
typealias Pool = List<Pair<Int, Int>>
typealias Bridge = List<Int>

class BridgeBuilder(input: List<String>) {

    val components: Pool = input.map {
        val tokens = it.split("/")
        tokens[0].toInt() to tokens[1].toInt()
    }

    fun computeBridgeScore(bridge: Bridge) = 2 * bridge.sum() - (bridge.lastOrNull() ?: 0) - (bridge.firstOrNull() ?: 0)

    fun findStrongestBridge() = findBestBridge(components, 0, Comparator { o1, o2 ->
        computeBridgeScore(o1) - computeBridgeScore(o2)
    })

    fun findLongestBridge() = findBestBridge(components, 0, Comparator { o1, o2 ->
        compareBridges(o1, o2)
    })

    fun findBestBridge(availables: Pool, start: Int, comp: Comparator<Bridge>): Bridge {
        val compatibleComponents = findPossibleMoves(start, availables)
        return if (compatibleComponents.isEmpty()) {
            listOf(start)
        } else {
            compatibleComponents.map {
                findBestBridge(availables - it, getOtherEnd(it, start), comp) + start
            }.maxWith(comp) ?: emptyList()
        }
    }

    fun findPossibleMoves(current: Int, pool: Pool) = pool.filter { it.first == current || it.second == current }

    private fun compareBridges(b1: Bridge, b2: Bridge): Int {
        return if (b1.size == b2.size) {
            computeBridgeScore(b1) - computeBridgeScore(b2)
        } else {
            b1.size - b2.size
        }
    }

    private fun getOtherEnd(component: Component, current: Int) = if (component.first == current) component.second else component.first
}