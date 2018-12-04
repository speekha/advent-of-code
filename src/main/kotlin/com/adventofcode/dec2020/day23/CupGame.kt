package com.adventofcode.dec2020.day23

class CupGame(input: String, extended: Boolean = false) {

    val configuration: String
        get() {
            val result = StringBuilder(cups.size)
            var index: Cup? = current
            repeat(cups.size) {
                result.append(index?.value)
                index = index?.next
            }
            return result.toString()
        }

    val labels: String
        get() {
            val conf = configuration
            return conf.dropWhile { it != '1' }.drop(1) + conf.takeWhile { it != '1' }
        }

    var cups = (input.map { Cup(it - '0') } +
            if (extended) ((input.length + 1)..1_000_000).map { Cup(it) } else emptyList())
        .also {
            it.forEachIndexed { index, cup ->
                cup.next = it[(index + 1) % it.size]
            }
        }.associateBy { it.value }

    var current = cups[input[0] - '0'] ?: error("")

    fun move() {
        val extraction = current.next
        val (picked, postGap) = extractCups(extraction)
        current.next = postGap
        val destId = computeDestination(picked)
        val destination = cups[destId] ?: error("Not found: $destId")
        val destinationNeighbor = destination.next
        destination.next = extraction
        extraction?.next?.next?.next = destinationNeighbor
        current = current.next ?: error("Hole in the chain")
    }

    private fun extractCups(next: Cup?): Pair<MutableSet<Int>, Cup?> {
        val picked = mutableSetOf<Int>()
        var postGap: Cup? = next
        repeat(3) {
            picked += postGap?.value ?: 0
            postGap = postGap?.next
        }
        return Pair(picked, postGap)
    }

    private fun computeDestination(exclude: Set<Int>): Int {
        var destId = ((current.value + cups.size - 2) % cups.size) + 1
        while (destId in exclude) {
            destId--
            if (destId < 1) {
                destId += cups.size
            }
        }
        return destId
    }
}