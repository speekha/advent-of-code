package com.adventofcode.dec2017.day8

class Instruction(
        val targetRegister: String,
        val operation: Operation,
        val step: Int,
        val condition: Condition)
