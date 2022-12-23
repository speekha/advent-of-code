package com.adventofcode.dec2022.day21

class MonkeyOperator(
    input: List<String>
) {

    val rules = mutableMapOf<String, Rule>().also { rules ->
        rules.putAll(input.map { it.split(": ") }.associate { it[0] to parseOperation(it[1], rules) })
    }

    private fun parseOperation(rule: String, ruleList: Map<String, Rule>): Rule = when {
        rule.contains(" + ") -> {
            val (a, b) = rule.split(" + ")
            Operation(a, b, ruleList, Long::plus)
        }

        rule.contains(" - ") -> {
            val (a, b) = rule.split(" - ")
            Operation(a, b, ruleList, Long::minus)
        }

        rule.contains(" * ") -> {
            val (a, b) = rule.split(" * ")
            Operation(a, b, ruleList, Long::times)
        }

        rule.contains(" / ") -> {
            val (a, b) = rule.split(" / ")
            Operation(a, b, ruleList, Long::div)
        }

        else -> Value(rule.toLong())
    }

    fun computeRiddleSolution(): Long = computeValue("root") ?: error("Missing some input")

    private fun computeValue(monkey: String): Long? {
        val result = rules[monkey]?.let { rule ->
            rule.eval().also {
                if (rule is Operation) rule.cache = it
            }
        }

        return result
    }

    private fun computeOperation(a: Long?, b: Long?, operator: (Long, Long) -> Long): Long? =
        if (a != null && b != null) operator(a, b) else null


    fun computeInput(): Long {
        var i = 0L
        rules.remove("humn")
        val root = rules["root"] as? Operation ?: error("No root")
        rules["root"] = Operation(root.aName, root.bName, rules, Long::minus)

        optimizeFixedValues()
        do {
            ++i
            println(i)
            rules.values.filterIsInstance(Operation::class.java).forEach { it.cache = null }
            rules["humn"] = Value(i)
        } while (computeValue("root") != 0L)
        return i
    }

    private fun optimizeFixedValues() {
        computeValue("root")
        val operations = rules.entries.filter { it.value is Operation }
        var i = 0
        operations.forEach { (key, value) ->
            val cache = (value as? Operation)?.cache
            if (cache != null) {
                i++
                rules[key] = Value(cache)
            }
        }
        println("Optimized $i rules")
    }

}