package com.adventofcode.dec2017.day23

sealed class Instruction {
    companion object {

        fun parseInstruction(input: String): Instruction {
            val tokens = input.split(" ")
            return when (tokens[0]) {
                "set" -> Set(tokens[1], tokens[2])
                "add" -> Add(tokens[1], tokens[2])
                "sub" -> Sub(tokens[1], tokens[2])
                "mul" -> Mul(tokens[1], tokens[2])
                "mod" -> Mod(tokens[1], tokens[2])
                "jnz" -> if (tokens[1].length != 1 || tokens[1][0] !in 'a'..'z') {
                    Jmp(tokens[2].toInt())
                } else {
                    Jnz(tokens[1], tokens[2])
                }
                else -> Idle()
            }
        }
    }

    abstract fun execute(program: AssemblyProcessor): Int

}

class Set(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(value)
        1
    }

    override fun toString(): String = "$register = $value"
}

class Add(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) + getRegister(value)
        1
    }

    override fun toString(): String = "$register += $value"
}

class Sub(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) - getRegister(value)
        1
    }

    override fun toString(): String = "$register -= $value"
}

class Mul(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) * getRegister(value)
        1
    }

    override fun toString(): String = "$register *= $value"
}

class Mod(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        registers[register] = getRegister(register) % getRegister(value)
        1
    }

    override fun toString(): String = "$register %= $value"
}

class Jmp(val value: Int) : Instruction() {
    override fun execute(program: AssemblyProcessor) = value

    override fun toString(): String = "goto $value"
}

class Jnz(val register: String, val value: String) : Instruction() {
    override fun execute(program: AssemblyProcessor) = with(program) {
        if (getRegister(register) != 0L) getRegister(value).toInt() else 1
    }

    override fun toString(): String = "if ($register != 0) goto $value"
}

class Idle : Instruction() {
    override fun execute(program: AssemblyProcessor): Int = 0

    override fun toString(): String = ""
}
