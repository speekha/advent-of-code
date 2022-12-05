package com.adventofcode.dec2020.day07

import java.util.*

class BagProcessor(input: List<String>) {

    val containerRules = input.map { parseRule(it) }.associateBy { it.container }

    fun findContainers(color: String): Set<String> {
        val containers = mutableSetOf<String>()
        val needles = LinkedList<String>()
        needles.push(color)
        while (needles.isNotEmpty()) {
            val needle = needles.pollFirst()
            containerRules.values.filter { it.content.any { it.second == needle } }
                .map { it.container }
                .forEach {
                    if (containers.add(it)) {
                        needles.add(it)
                    }
                }
        }
        return containers
    }

    fun findContent(color: String): Int {
        val rule = containerRules[color]?.content ?: error("Data error")
        return 1 + rule.sumOf { (count, color) ->
            count * findContent(color)
        }
    }

    companion object {
        fun parseRule(input: String): Rule {
            val (container, content) = input.split(" bags contain ")
            val bags = if (content == "no other bags.") {
                emptyList()
            } else {
                content.split(", ").map {
                    val item = it.replace(" bags", "")
                        .replace(".", "")
                        .replace(" bag", "")
                    val number = item.take(item.indexOf(' ')).toInt()
                    number to item.drop(item.indexOf(' ') + 1)
                }
            }
            return Rule(container, bags)
        }
    }
}