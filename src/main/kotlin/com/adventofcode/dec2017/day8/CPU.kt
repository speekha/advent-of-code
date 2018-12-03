package com.adventofcode.dec2017.day8

import java.lang.Integer.max
import java.lang.Integer.min

class CPU(
        initState: Map<String, Int> = mapOf()
) {

    private val registers = mutableMapOf<String, Int>().apply {
        putAll(initState)
    }

    var min = 0

    var max = 0

    fun processInstructions(inputs: List<Instruction>) {
        inputs.forEach {
            if (checkCondition(it.condition)) {
                updateRegister(it)
                max = max(max, getRegisterOrInit(it.targetRegister))
                min = min(min, getRegisterOrInit(it.targetRegister))
            }
        }
    }

    fun checkCondition(condition: Condition): Boolean = when (condition.operator) {
        Operator.EQUALS -> getRegisterOrInit(condition.left) == condition.right.toInt()
        Operator.NOT_EQUALS -> getRegisterOrInit(condition.left) != condition.right.toInt()
        Operator.GREATER_THAN -> getRegisterOrInit(condition.left) > condition.right.toInt()
        Operator.GREATER_OR_EQUAL -> getRegisterOrInit(condition.left) >= condition.right.toInt()
        Operator.LESS_THAN -> getRegisterOrInit(condition.left) < condition.right.toInt()
        Operator.LESS_OR_EQUAL -> getRegisterOrInit(condition.left) <= condition.right.toInt()
    }

    fun getRegisterOrInit(register: String): Int {
        return registers.getOrPut(register) { 0 }
    }

    private fun updateRegister(instruction: Instruction) = with(instruction) {
        registers[targetRegister] = when (operation) {
            Operation.INC -> getRegisterOrInit(targetRegister) + step
            Operation.DEC -> getRegisterOrInit(targetRegister) - step
        }
    }

    fun getHighestRegister() = registers.maxBy { it.value } ?: error("Not ready")

    fun getRange() = min..max
}