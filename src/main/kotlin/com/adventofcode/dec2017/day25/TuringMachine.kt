package com.adventofcode.dec2017.day25

data class TuringMachine(
        var state: String,
        val diagnostic: Int,
        val rules: Map<String, Rule>
) {

    private val memory = MemorySlot()

    private var cursor = memory

    fun runDiagnostic(): Int {
        iterate(diagnostic)
        return checksum()
    }

    fun iterate(steps: Int) {
        for (i in 1..steps) {
            val rule = rules[state] ?: error("No matching state")
            val command = if (cursor.value == 0) rule.ifZero else rule.ifOne
            applyCommand(command)
        }
    }

    fun checksum(): Int {
        return memory.value + sumLeft() + sumRight()
    }

    private fun applyCommand(command: Command) {
        cursor.value = command.write
        cursor = moveCursor(command)
        state = command.next
    }

    private fun moveCursor(command: Command) = if (command.move == Direction.LEFT) {
        cursor.getOrAddLeft()
    } else {
        cursor.getOrAddRight()
    }

    private fun sumLeft(): Int {
        var slot = memory.left
        var total = 0
        while (slot != null) {
            total += slot.value
            slot = slot.left
        }
        return total
    }

    private fun sumRight(): Int {
        var slot = memory.right
        var total = 0
        while (slot != null) {
            total += slot.value
            slot = slot.right
        }
        return total
    }

    companion object {
        fun fromString(input: List<String>): TuringMachine {
            val state = getLastToken(input[0])
            val tokens = input[1].split(" ")
            val steps = tokens[tokens.size - 2].toInt()
            val rules = (3..input.size step 10)
                    .asSequence()
                    .map { Rule.fromString(input.subList(it, it + 9)) }
                    .associate { it.id to it }
            return TuringMachine(state, steps, rules)
        }
    }
}