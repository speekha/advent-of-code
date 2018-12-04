package com.adventofcode.dec2020.day18

class MathProcessor(
    private val priorities: Boolean
) {

    private val matcher = Regex("""\([^()]*\)""")

    fun evaluate(input: String): Long {
        var expression = input
        while (expression.contains("(")) {
            val inner: MatchResult = matcher.find(expression) ?: error("No more parenthesis")
            expression = expression.take(inner.range.first) +
                    evaluateGroup(inner.value.removeParentheses()) +
                    expression.drop(inner.range.last + 1)
        }
        return evaluateGroup(expression)
    }

    private fun String.removeParentheses() = substring(1 until length - 1)

    private fun evaluateGroup(input: String) = if (priorities) {
        evaluateWithPriorities(input)
    } else {
        evaluatePlainExpression(input)
    }

    private fun evaluateWithPriorities(input: String): Long {
        val tokens = input.split(" * ")
        return tokens.fold(1) { acc, s ->
            acc * evaluatePlainExpression(s)
        }
    }

    private fun evaluatePlainExpression(input: String): Long {
        val tokens = input.split(" ")
        return tokens.drop(1)
            .chunked(2)
            .fold(tokens[0].toLong()) { acc, list ->
                when (val operator = list[0]) {
                    "+" -> acc + list[1].toLong()
                    "*" -> acc * list[1].toLong()
                    else -> error("Unknown operator $operator")
                }
            }
    }
}