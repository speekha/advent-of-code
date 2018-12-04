package com.adventofcode.dec2018.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RecipeScorerTest {

    @Test
    fun `should init recipes`() {
        val recipeTester = RecipeScorer()
        Assertions.assertEquals(listOf(3, 7), recipeTester.recipes)
    }

    @Test
    fun `should compute first iteration`() {
        val recipeTester = RecipeScorer()
        recipeTester.iterate(1)
        Assertions.assertEquals(listOf(3, 7, 1, 0), recipeTester.recipes)
    }

    @Test
    fun `should compute 15 first iterations`() {
        val recipeTester = RecipeScorer()
        recipeTester.iterate(15)
        Assertions.assertEquals(listOf(3, 7, 1, 0, 1, 0, 1, 2, 4, 5, 1, 5, 8, 9, 1, 6, 7, 7, 9, 2), recipeTester.recipes)
    }

    @Test
    fun `should compute score`() {
        val recipeTester = RecipeScorer()
        Assertions.assertEquals("5158916779", recipeTester.computeScore(9))
    }

    @Test
    fun `should compute actual score`() {
        val recipeTester = RecipeScorer()
        Assertions.assertEquals("1342316410", recipeTester.computeScore(47801))
    }

    @Test
    fun `should count recipes`() {
        val recipeTester = RecipeScorer()
        Assertions.assertEquals(9, recipeTester.countRecipes("51589"))
    }

    @Test
    fun `should count 2018 recipes`() {
        val recipeTester = RecipeScorer()
        Assertions.assertEquals(2018, recipeTester.countRecipes("59414"))
    }

    @Test
    fun `should count actual recipes`() {
        val recipeTester = RecipeScorer()
        Assertions.assertEquals(20235230, recipeTester.countRecipes("047801"))
    }

}