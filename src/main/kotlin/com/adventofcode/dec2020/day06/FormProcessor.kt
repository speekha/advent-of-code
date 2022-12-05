package com.adventofcode.dec2020.day06

class FormProcessor(val input: List<String>) {

    val data: List<String> = input.joinToString(" ").split("  ")

    fun countQuestions(): Int = data.sumOf {
        it.replace(" ", "").toCharArray().toSet().size
    }

    fun countCommonQuestions(): Int = data.sumOf {
        val group = it.split(" ")
        group.fold(('a'..'z').toList()) { common, answers ->
            common.intersect(answers.toCharArray().toSet()).toList()
        }.size
    }
}
