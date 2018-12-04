package com.adventofcode.dec2015.day07

interface Gate {
    val output: Int
}

class Constant(
    val input: String,
    val wires: (String) -> Int
) : Gate {
    override val output: Int by lazy { wires(input) }
}

class And(
    val a: String,
    val b: String,
    val wires: (String) -> Int
) : Gate {
    override val output: Int by lazy { wires(a) and wires(b) }
}

class Or(
    val a: String,
    val b: String,
    val wires: (String) -> Int
) : Gate {
    override val output: Int by lazy { wires(a) or wires(b) }
}

class Not(
    val a: String,
    val wires: (String) -> Int
) : Gate {
    override val output: Int by lazy { wires(a).inv() and 65535 }
}

class LeftShift(
    val a: String,
    val offset: Int,
    val wires: (String) -> Int
) : Gate {
    override val output: Int by lazy { wires(a) shl offset }
}

class RightShift(
    val a: String,
    val offset: Int,
    val wires: (String) -> Int
) : Gate {
    override val output: Int by lazy { wires(a) shr offset }
}