package com.adventofcode.dec2017.day12

import org.junit.Assert
import org.junit.Test

class NetworkManagerTest {
    val input = listOf(
            "0 <-> 2",
            "1 <-> 1",
            "2 <-> 0, 3, 4",
            "3 <-> 2, 4",
            "4 <-> 2, 3, 6",
            "5 <-> 6",
            "6 <-> 4, 5")

    @Test
    fun `should have 7 programs`() {
        val manager = NetworkManager()
        manager.parse(input)
        Assert.assertEquals(7, manager.programs.size)
    }

    @Test
    fun `programs should have siblings`() {
        val manager = NetworkManager()
        manager.parse(input)
        Assert.assertEquals(listOf(0, 3, 4), manager.programs[2])
    }

    @Test
    fun `group should have 6 nodes`() {
        val manager = NetworkManager()
        manager.parse(input)
        Assert.assertEquals(6, manager.browseGroup(0).size)
    }

    @Test
    fun `should have 2 groups`() {
        val manager = NetworkManager()
        manager.parse(input)
        Assert.assertEquals(2, manager.countGroups())
    }
}