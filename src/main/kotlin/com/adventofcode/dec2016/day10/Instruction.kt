package com.adventofcode.dec2016.day10

sealed class Instruction

data class Transfer(
        val source: Int,
        val lowOutputType: OutputType,
        val lowOutput: Int,
        val highOutputType: OutputType,
        val highOutput: Int
) : Instruction() {

    constructor(input: List<String>) : this(
            input[1].toInt(),
            OutputType.valueOf(input[5]),
            input[6].toInt(),
            OutputType.valueOf(input[10]),
            input[11].toInt())

}

data class Inject(
        val chip: Int,
        val bot: Int
) : Instruction() {

    constructor(input: List<String>) : this(
            input[1].toInt(),
            input[5].toInt())
}

class NullInstruction : Instruction()