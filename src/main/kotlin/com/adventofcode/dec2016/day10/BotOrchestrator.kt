package com.adventofcode.dec2016.day10

import java.io.File
import kotlin.math.max

class BotOrchestrator(input: List<String>) {

    val instructions = input.map { parseInstruction(it) }

    val bots = Array(computeBotNumber()) { ProcessingBot(it) }

    val outputs = IntArray(computeOutputNumber()) {-1}

    val flags = BooleanArray(bots.size)

    fun initFlows() {
        instructions.forEach {
            when (it) {
                is Inject -> bots[it.bot].inputs += it.chip
                is Transfer -> {
                    bots[it.source].lowOutput = it.lowOutput
                    bots[it.source].lowOutputType = it.lowOutputType
                    bots[it.source].highOutput = it.highOutput
                    bots[it.source].highOutputType = it.highOutputType
                }
            }
        }
    }

    fun findComparator(vararg values: Int): ProcessingBot {
        while (!bots.any { it.inputs == values.toSet() }) {
            processTransitions()
        }
        return bots.first { it.inputs == values.toSet() }
    }

    fun computeOutput(i: Int): Int {
        while (outputs[i] == -1) {
            processTransitions()
        }
        return outputs[i]
    }

    private fun processTransitions() {
        bots.indices
                .filter { !flags[it] && bots[it].inputs.size == 2 }
                .forEach {
                    bots[it].lowOutput?.let { i ->
                        if (bots[it].lowOutputType == OutputType.bot) {
                            bots[i].inputs += bots[it].inputs.minOrNull()!!
                        } else {
                            outputs[i] = bots[it].inputs.minOrNull()!!
                        }
                    }
                    bots[it].highOutput?.let { i ->
                        if (bots[it].highOutputType == OutputType.bot) {
                            bots[i].inputs += bots[it].inputs.maxOrNull()!!
                        } else {
                            outputs[i] = bots[it].inputs.maxOrNull()!!
                        }
                    }
                    flags[it] = true
                }
    }

    private fun computeBotNumber(): Int {
        var max = 0
        instructions.forEach {
            when (it) {
                is Inject -> max = max(max, it.bot)
                is Transfer -> {
                    if (it.lowOutputType == OutputType.bot) {
                        max = max(max, it.lowOutput)
                    }
                    if (it.highOutputType == OutputType.bot) {
                        max = max(max, it.highOutput)
                    }
                }
            }
        }
        return max + 1
    }

    private fun computeOutputNumber(): Int {
        var max = 0
        instructions.forEach {
            if (it is Transfer) {
                if (it.lowOutputType == OutputType.output) {
                    max = max(max, it.lowOutput)
                }
                if (it.highOutputType == OutputType.output) {
                    max = max(max, it.highOutput)
                }
            }
        }
        return max + 1
    }

    private fun parseInstruction(input: String): Instruction = when {
        input.startsWith("value") -> parseInjectInstruction(input)
        input.startsWith("bot") -> parseTransferInstruction(input)
        else -> NullInstruction()
    }

    private fun parseTransferInstruction(input: String): Transfer {
        return Transfer(input.split(" "))
    }

    private fun parseInjectInstruction(input: String): Inject {
        return Inject(input.split(" "))
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day10/input.txt").readLines()
    with(BotOrchestrator(input)) {
        initFlows()
        println("Bot sorting 17 and 61: ${findComparator(17, 61).id}")
        val output0 = computeOutput(0)
        val output1 = computeOutput(1)
        val output2 = computeOutput(2)
        println("Outputs contain $output0, $output1, $output2. Total is ${output0 * output1 * output2}")
    }
}