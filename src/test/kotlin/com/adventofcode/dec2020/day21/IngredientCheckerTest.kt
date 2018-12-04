package com.adventofcode.dec2020.day21

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IngredientCheckerTest {

    val input = listOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)"
    )

    @Test
    fun `should list ingredients with allergens`() {
        val checker = IngredientChecker(input)
        assertEquals(
            Food(listOf("mxmxvkd", "kfcds", "sqjhc", "nhms"), listOf("dairy", "fish")),
            checker.foods[0]
        )
    }

    @Test
    fun `should find allergen free ingredients`() {
        val checker = IngredientChecker(input)
        assertEquals(
            setOf("kfcds", "nhms", "sbzzf", "trh"),
            checker.identifyAllergenFreeIngredient()
        )
    }

    @Test
    fun `should count allergen free uses`() {
        val checker = IngredientChecker(input)
        assertEquals(
            5,
            checker.countAllergenFreeUses()
        )
    }

    @Test
    fun `should count actual allergen free uses`() {
        val checker = IngredientChecker(readInputAsList())
        assertEquals(
            2020,
            checker.countAllergenFreeUses()
        )
    }

    @Test
    fun `should list allergens`() {
        val checker = IngredientChecker(input)
        assertEquals(
            "mxmxvkd,sqjhc,fvjkl",
            checker.canonicalAllergenList()
        )
    }

    @Test
    fun `should list actual allergens`() {
        val checker = IngredientChecker(readInputAsList())
        assertEquals(
            "bcdgf,xhrdsl,vndrb,dhbxtb,lbnmsr,scxxn,bvcrrfbr,xcgtv",
            checker.canonicalAllergenList()
        )
    }

}