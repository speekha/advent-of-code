package com.adventofcode.dec2019.day14

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class FuelSynthesizerTest {

    val input = listOf(
            "10 ORE => 10 A",
            "1 ORE => 1 B",
            "7 A, 1 B => 1 C",
            "7 A, 1 C => 1 D",
            "7 A, 1 D => 1 E",
            "7 A, 1 E => 1 FUEL")

    @Test
    fun `should parse input`() {

        val reactor = FuelSynthesizer(input)
        assertEquals(6, reactor.reactions.size)
        assertEquals(Reaction(listOf(Chemical("ORE", 10)), Chemical("A", 10)), reactor.reactions["A"])
    }

    @Test
    fun `should compute need of ore`() {
        val reactor = FuelSynthesizer(input)
        assertEquals(31, reactor.calculateNeededOre())
    }

    @Test
    fun `should compute actual need`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day14/input.txt").readLines()
        val reactor = FuelSynthesizer(input)
        assertEquals(337075, reactor.calculateNeededOre())
    }

    @Test
    fun `should compute max fuel for example 1`() {
        val input = listOf("157 ORE => 5 NZVS",
                "165 ORE => 6 DCFZ",
                "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL",
                "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ",
                "179 ORE => 7 PSHF",
                "177 ORE => 5 HKGWZ",
                "7 DCFZ, 7 PSHF => 2 XJWVT",
                "165 ORE => 2 GPVTF",
                "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT")
        val reactor = FuelSynthesizer(input)
        assertEquals(82892753,reactor.computeMaxFuelFor(1000000000000))
    }
    @Test
    fun `should compute max fuel for example 2`() {
        val input = listOf("171 ORE => 8 CNZTR",
                "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL",
                "114 ORE => 4 BHXH",
                "14 VRPVC => 6 BMBT",
                "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL",
                "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT",
                "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW",
                "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW",
                "5 BMBT => 4 WPTQ",
                "189 ORE => 9 KTJDG",
                "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP",
                "12 VRPVC, 27 CNZTR => 2 XDBXC",
                "15 KTJDG, 12 BHXH => 5 XCVML",
                "3 BHXH, 2 VRPVC => 7 MZWV",
                "121 ORE => 7 VRPVC",
                "7 XCVML => 6 RJRHP",
                "5 BHXH, 4 VRPVC => 5 LTCX")
        val reactor = FuelSynthesizer(input)
        assertEquals(460664,reactor.computeMaxFuelFor(1000000000000))
    }

    @Test
    @Disabled
    fun `should compute max fuel`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day14/input.txt").readLines()
        val reactor = FuelSynthesizer(input)
        assertEquals(5194174, reactor.computeMaxFuelFor(1000000000000))
    }
}