package com.adventofcode.dec2020.day21

class IngredientChecker(input: List<String>) {
    val foods = input.map { parseIngredient(it) }

    val allergens = foods.flatMapIndexed { index, food ->
        food.allergens.map { it to index }
    }.groupBy { it.first }.mapValues { it.value.map { it.second } }

    private fun parseIngredient(data: String): Food {
        val parenthesis = data.indexOf('(')
        return if (parenthesis == -1) {
            Food(data.split(", "), emptyList())
        } else {
            val name = data.take(parenthesis - 1).split(" ")
            val allergens = data.drop(parenthesis + 10).dropLast(1).split(", ")
            Food(name, allergens)
        }
    }

    fun identifyAllergenFreeIngredient(): Set<String> {
        val identified = identifyAllergens()
        return foods.flatMap { it.ingredients }.toSet() - identified.values.toSet()
    }

    fun identifyAllergens(): Map<String, String> {
        var options: Map<String, List<Set<String>>> = allergens.mapValues { (_, value) ->
            value.map { foods[it].ingredients.toSet() }
        }
        val identified = mutableMapOf<String, String>()
        while (identified.size < allergens.size) {
            val reduced = options.mapValues { (_, suspects) ->
                suspects.reduce { result, list -> result.intersect(list) }
            }
            identified += reduced.filter { it.value.size == 1 }.mapValues { it.value.first() }
            options = options.filterKeys { !identified.containsKey(it) }.mapValues { entry ->
                entry.value.map { it: Set<String> -> it - identified.values }.filter { it.isNotEmpty() }
            }
        }
        return identified
    }

    fun countAllergenFreeUses(): Int {
        val allergenFree = identifyAllergenFreeIngredient()
        return foods.flatMap { it.ingredients }.filter { it in allergenFree }.size
    }

    fun canonicalAllergenList(): String =
        identifyAllergens().entries.sortedBy { it.key }.joinToString(",") { it.value }
}