package com.adventofcode.dec2016.day25

sealed class Instruction

data class Cpy(val x: String, val y: String) : Instruction()

data class Inc(val x: String) : Instruction()

data class Dec(val x: String) : Instruction()

data class Jnz(val x: String, val y: String) : Instruction()

data class Tgl(val x: String) : Instruction()

data class Out(val x: String) : Instruction()