package com.adventofcode.dec2020.day19

class RuleMatcher(input: List<String>) {

    val rules: Map<String, Rule> = input.takeWhile { it.isNotEmpty() }.associate { parseRule(it) }

    val messages: List<String> = input.drop(rules.size + 1)

    private val matchers: MutableMap<String, String> = mutableMapOf()

    private val m42: String
        get() = matchers["42"] ?: ""
    private val m31: String
        get() = matchers["31"] ?: ""

    init {
        matchers.putAll(rules.mapValues { it.value.buildRegex() })
    }

    private fun parseRule(input: String): Pair<String, Rule> {
        val (index, rule) = input.split(": ")
        return index to when {
            rule.contains("|") ->
                CompoundRule(rule.split(" | ").map { SequenceRule(it.split(" ").toList()) })
            rule.contains('"') ->
                Token(rule.replace("\"", ""))
            else ->
                SequenceRule(rule.split(" ").toList())
        }
    }

    fun countValidMessages(): Int = messages.count { checkMessageValidity(it) }

    fun countAlternateValidMessages(): Int {
        alterRules()
        val m42 = Regex(matchers["42"] ?: "")
        return messages.count { message ->
            checkSpecialValidity(message, m42)
        }
    }

    private fun checkSpecialValidity(message: String, m42: Regex) = if (checkMessageValidity(message)) {
        val count42 = m42.findAll(message)
        (1..count42.count()).any { i ->
            val pattern = matchers["0-$i"] ?: error("No matcher for 0-$i")
            Regex(pattern).matches(message)
        }
    } else false

    private fun alterRules() {
        matchers["0"] = "($m42)+($m31)+"
        (1..10).forEach { i ->
            matchers["0-$i"] = matcher(i)
        }
    }

    private fun matcher(count: Int): String = "($m42){${count + 1}}($m31){1,$count}"

    fun checkMessageValidity(message: String): Boolean {
        val regex = matchers["0"] ?: error("Missing regexp for rule 0")
        return Regex(regex).matches(message)
    }

    private fun Rule.buildRegex(): String = when (this) {
        is Token -> value
        is SequenceRule -> steps.joinToString("") { matchers[it] ?: rules[it]?.buildRegex() ?: "" }
        is CompoundRule -> segments.joinToString("|", "(", ")") { it.buildRegex() }
    }
}