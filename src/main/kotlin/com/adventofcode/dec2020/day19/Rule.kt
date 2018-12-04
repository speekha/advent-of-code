package com.adventofcode.dec2020.day19

sealed class Rule

data class Token(
    val value: String
) : Rule()

data class CompoundRule(
    val segments: List<Rule>
) : Rule()

data class SequenceRule(
    val steps: List<String>
) : Rule()