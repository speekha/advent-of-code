package com.adventofcode.dec2019.day14

import java.util.*

class FuelSynthesizer(
        input: List<String>
) {
    val reactions = input.map { parseReaction(it) }.associate { it }

    private fun parseReaction(reaction: String): Pair<String, Reaction> {
        val (inputs, output) = reaction.split(" => ")
        val inputChemicals = inputs.split(", ").map { Chemical(it) }
        val outputChemical: Chemical = Chemical(output)
        return outputChemical.element to Reaction(inputChemicals, outputChemical)
    }

    fun calculateNeededOre(leftOvers: MutableMap<String, Int> = mutableMapOf()): Int {
        var totalOre = 0
        val queue = LinkedList<Chemical>()
        queue.add(Chemical("FUEL", 1))
        while (queue.isNotEmpty()) {
            val need = queue.pop()
            val reaction = reactions[need.element] ?: error("Incorrect reaction")
            var neededQuantity = need.quantity
            val available = leftOvers.getOrDefault(need.element, 0)
            if (neededQuantity <= available) {
                leftOvers[need.element] = available - neededQuantity
            } else {
                neededQuantity -= available
                leftOvers[need.element] = 0
                var numberOfReactions = neededQuantity / reaction.output.quantity
                val excess = neededQuantity % reaction.output.quantity
                if (excess > 0) {
                    numberOfReactions++
                    leftOvers[need.element] = reaction.output.quantity - excess + leftOvers.getOrDefault(need.element, 1)
                }
                if (reaction.inputs.size == 1 && reaction.inputs[0].element == "ORE") {
                    totalOre += reaction.inputs[0].quantity * numberOfReactions
                } else {
                    reaction.inputs.forEach {
                        queue.add(Chemical(it.element, it.quantity * numberOfReactions))
                    }
                }
            }
        }
        return totalOre
    }

    fun computeMaxFuelFor(oreQuantity: Long): Long {
        var fuel = 0L
        var ore = 0L
        val leftOvers = mutableMapOf<String, Int>()
        while (ore < oreQuantity) {
            val neededOre = calculateNeededOre(leftOvers)
            ore += neededOre
            if (ore <= oreQuantity) {
                fuel++
            }
            if (leftOvers.all { it.value == 0 }) {
                val cycles = oreQuantity / ore
                fuel *= cycles
                ore *= cycles
            }
        }
        return fuel
    }
}
