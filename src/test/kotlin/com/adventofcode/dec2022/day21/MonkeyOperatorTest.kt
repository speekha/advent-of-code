package com.adventofcode.dec2022.day21

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class MonkeyOperatorTest {

    val input = listOf(
        "root: pppw + sjmn",
        "dbpl: 5",
        "cczh: sllz + lgvd",
        "zczc: 2",
        "ptdq: humn - dvpt",
        "dvpt: 3",
        "lfqf: 4",
        "humn: 5",
        "ljgn: 2",
        "sjmn: drzm * dbpl",
        "sllz: 4",
        "pppw: cczh / lfqf",
        "lgvd: ljgn * ptdq",
        "drzm: hmdt - zczc",
        "hmdt: 32"
    )

    @Test
    fun `should find solution for simple number`() {
        val operator = MonkeyOperator(listOf("root: 3"))
        assertEquals(3, operator.computeRiddleSolution())
    }

    @Test
    fun `should find solution for addition`() {
        val operator = MonkeyOperator(listOf("root: aaaa + bbbb", "aaaa: 3", "bbbb: 5"))
        assertEquals(8, operator.computeRiddleSolution())
    }
    @Test
    fun `should find solution for substraction`() {
        val operator = MonkeyOperator(listOf("root: aaaa - bbbb", "aaaa: 5", "bbbb: 3"))
        assertEquals(2, operator.computeRiddleSolution())
    }

    @Test
    fun `should find solution for multiplication`() {
        val operator = MonkeyOperator(listOf("root: aaaa * bbbb", "aaaa: 3", "bbbb: 5"))
        assertEquals(15, operator.computeRiddleSolution())
    }
    @Test
    fun `should find solution for division`() {
        val operator = MonkeyOperator(listOf("root: aaaa / bbbb", "aaaa: 9", "bbbb: 3"))
        assertEquals(3, operator.computeRiddleSolution())
    }

    @Test
    fun `should compute riddle solution`() {
        val operator = MonkeyOperator(input)
        assertEquals(152, operator.computeRiddleSolution())
    }

    @Test
    fun `should compute actual riddle solution`() {
        val operator = MonkeyOperator(actualInputList)
        assertEquals(379578518396784, operator.computeRiddleSolution())
    }


    @Test
    fun `should find missing variable`() {
        val operator = MonkeyOperator(input)
        assertEquals(301, operator.computeInput())
    }



    @Test
    @Ignore("Does not complete yet")
    fun `should find actual missing variable`() {
        val operator = MonkeyOperator(actualInputList)
        assertEquals(301, operator.computeInput())
    }

}