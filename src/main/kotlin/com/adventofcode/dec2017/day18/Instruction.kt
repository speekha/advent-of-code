package com.adventofcode.dec2017.day18

sealed class Instruction {
    companion object {
        fun parseInstruction(input: String): Instruction {
            val tokens = input.split(" ")
            return when (tokens[0]) {
                "snd" -> Snd(tokens[1])
                "set" -> Set(tokens[1], tokens[2])
                "add" -> Add(tokens[1], tokens[2])
                "mul" -> Mul(tokens[1], tokens[2])
                "mod" -> Mod(tokens[1], tokens[2])
                "rcv" -> Rcv(tokens[1])
                "jgz" -> Jgz(tokens[1], tokens[2])
                else -> Idle()
            }
        }
    }

    abstract fun execute(program: AssemblyProcessor): Int

}

class Snd(val register: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        send(getRegister(register))
        1
    }
}

class Set(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(value)
        1
    }
}

class Add(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) + getRegister(value)
        1
    }
}

class Mul(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) * getRegister(value)
        1
    }
}

class Mod(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) % getRegister(value)
        1
    }
}

class Rcv(val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        receive(value)
    }
}

class Jgz(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        if (getRegister(register) > 0) getRegister(value).toInt() else 1
    }

}

class Idle() : Instruction() {
    override fun execute(program: AssemblyProcessor): Int = 0
}
