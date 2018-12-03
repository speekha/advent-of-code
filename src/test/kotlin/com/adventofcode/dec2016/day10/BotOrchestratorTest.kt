package com.adventofcode.dec2016.day10

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class BotOrchestratorTest {

    val input = listOf("value 5 goes to bot 2",
            "bot 2 gives low to bot 1 and high to bot 0",
            "value 3 goes to bot 1",
            "bot 1 gives low to output 1 and high to bot 0",
            "bot 0 gives low to output 2 and high to output 0",
            "value 2 goes to bot 2")

    @Test
    fun `should have 3 bots`() {
        Assert.assertEquals(3, BotOrchestrator(input).bots.size)
    }

    @Test
    fun `should have correct flows`() {
        val sched = BotOrchestrator(input)
        sched.initFlows()
        val bots = sched.bots

        Assert.assertEquals(setOf(2, 5), bots[2].inputs)
        Assert.assertEquals(1, bots[2].lowOutput)
        Assert.assertEquals(0, bots[2].highOutput)
    }

    @Test
    fun `find bot without computation`() {
        val sched = BotOrchestrator(input)
        sched.initFlows()
        println(sched.bots.toList())
        Assert.assertEquals(2, sched.findComparator(2, 5).id)
    }

    @Test
    fun `find bot with computation`() {
        val sched = BotOrchestrator(input)
        sched.initFlows()
        println(sched.bots.toList())
        Assert.assertEquals(1, sched.findComparator(2, 3).id)
        Assert.assertEquals(0, sched.findComparator(3, 5).id)
    }

    @Test
    fun `find output without computation`() {
        val sched = BotOrchestrator(input)
        sched.initFlows()
        println(sched.bots.toList())
        Assert.assertEquals(2, sched.computeOutput(1))
    }

    @Test
    fun `find output with computation`() {
        val sched = BotOrchestrator(input)
        sched.initFlows()
        println(sched.bots.toList())
        Assert.assertEquals(5, sched.computeOutput(0))
        Assert.assertEquals(3, sched.computeOutput(2))
    }
}