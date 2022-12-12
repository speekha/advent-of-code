package com.adventofcode.dec2022.day11

import com.adventofcode.actualInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MonkeyTest {

    val input = "Monkey 0:\n" +
            "  Starting items: 79, 98\n" +
            "  Operation: new = old * 19\n" +
            "  Test: divisible by 23\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 1:\n" +
            "  Starting items: 54, 65, 75, 74\n" +
            "  Operation: new = old + 6\n" +
            "  Test: divisible by 19\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 0\n" +
            "\n" +
            "Monkey 2:\n" +
            "  Starting items: 79, 60, 97\n" +
            "  Operation: new = old * old\n" +
            "  Test: divisible by 13\n" +
            "    If true: throw to monkey 1\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 3:\n" +
            "  Starting items: 74\n" +
            "  Operation: new = old + 3\n" +
            "  Test: divisible by 17\n" +
            "    If true: throw to monkey 0\n" +
            "    If false: throw to monkey 1"

    @Test
    fun `should parse monkeys`() {
        assertEquals(
            listOf(
                listOf(79L, 98L),
                listOf(54L, 65L, 75L, 74L),
                listOf(79L, 60L, 97L),
                listOf(74L),
            ), MonkeyManager(input).monkeys.map { it.items }
        )
    }

    @Test
    fun `should let monkey process items`() {
        val manager = MonkeyManager(input)
        assertTrue(manager.monkeys[0].hasItem())
        assertEquals(Pair(500L, 3), manager.monkeys[0].processItem(3, Long.MAX_VALUE))
    }

    @Test
    fun `should pass items around`() {
        val manager = MonkeyManager(input)
        manager.processInspections(1)
        assertEquals(
            listOf(
                listOf(20L, 23L, 27L, 26L),
                listOf(2080L, 25L, 167L, 207L, 401L, 1046L),
                listOf(),
                listOf(),
            ), manager.monkeys.map { it.items as List<Long> }
        )
    }

    @Test
    fun `should count inspections`() {
        val manager = MonkeyManager(input)
        manager.processInspections(20)
        assertEquals(listOf(101, 95, 7, 105), manager.monkeys.map { it.inspections })
        assertEquals(10605, manager.computeWorryLevel())
    }

    @Test
    fun `should count actual inspections`() {
        val manager = MonkeyManager(actualInput)
        manager.processInspections(20)
        assertEquals(67830, manager.computeWorryLevel())
    }

    @Test
    fun `should count more inspections`() {
        val manager = MonkeyManager(input, 1)
        manager.processInspections(1)
        assertEquals(listOf(2, 4, 3, 6), manager.monkeys.map { it.inspections })
        manager.processInspections(19)
        assertEquals(listOf(99, 97, 8, 103), manager.monkeys.map { it.inspections })
        manager.processInspections(9980)
        assertEquals(listOf(52166, 47830, 1938, 52013), manager.monkeys.map { it.inspections })
        assertEquals(2713310158, manager.computeWorryLevel())
    }

    @Test
    fun `should count more actual inspections`() {
        val manager = MonkeyManager(actualInput, 1)
        manager.processInspections(10000)
        assertEquals(15305381442, manager.computeWorryLevel())
    }
}