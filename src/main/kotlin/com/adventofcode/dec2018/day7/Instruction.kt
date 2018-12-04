package com.adventofcode.dec2018.day7

data class Instruction (
        val id: String,
        val prerequisite: MutableSet<String> = mutableSetOf(),
        val nextSteps: MutableSet<String> = mutableSetOf()
)