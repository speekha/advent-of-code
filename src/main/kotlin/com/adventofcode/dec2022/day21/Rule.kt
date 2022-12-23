package com.adventofcode.dec2022.day21

sealed class Rule {
    abstract fun eval(): Long?
}

class Operation(
    val aName: String,
    val bName: String,
    val rules: Map<String, Rule>,
    val operator: (Long, Long) -> Long
) : Rule() {
    var cache: Long? = null

    override fun eval(): Long? {
        val a = rules[aName]?.eval()
        val b = rules[bName]?.eval()
        return when {
            cache != null -> cache
            a != null && b != null -> operator(a, b).also { cache = it }
            else -> null
        }
    }
}

class Value(val value: Long) : Rule() {
    override fun eval(): Long = value
}