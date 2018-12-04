package com.adventofcode.dec2015.day07

class CircuitEmulator(input: List<String>) {

    val gates: Map<String, Gate> = input.associate { parseGate(it) }

    private fun parseGate(input: String): Pair<String, Gate> {
        val (command, output) = input.split((" -> "))
        return output to when {
            command.contains(" AND ") -> andGate(command)
            command.contains(" OR ") -> orGate(command)
            command.contains(" LSHIFT ") -> lShiftGate(command)
            command.contains(" RSHIFT ") -> rShiftGate(command)
            command.contains("NOT ") -> notGate(command)
            else -> Constant(command, this::getWire)
        }
    }

    private fun notGate(command: String): Not {
        val a = command.drop(4)
        return Not(a, this::getWire)
    }

    private fun rShiftGate(command: String): RightShift {
        val (a, b) = command.split(" RSHIFT ")
        return RightShift(a, b.toInt(), this::getWire)
    }

    private fun lShiftGate(command: String): LeftShift {
        val (a, b) = command.split(" LSHIFT ")
        return LeftShift(a, b.toInt(), this::getWire)
    }

    private fun orGate(command: String): Or {
        val (a, b) = command.split(" OR ")
        return Or(a, b, this::getWire)
    }

    private fun andGate(command: String): And {
        val (a, b) = command.split(" AND ")
        return And(a, b, this::getWire)
    }

    fun getWire(input: String) = gates[input]?.output ?: input.toInt()

}