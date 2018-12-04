package com.adventofcode.dec2021.day08

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DigitalDisplayTest {

    val singleInput = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"

    val input = listOf(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    )

    @Test
    fun `should find some digits`() {
        val display = DigitalDisplay()
        assertEquals(26, display.count1478(input))
    }

    @Test
    fun `should find some digits in actual input`() {
        val display = DigitalDisplay()
        assertEquals(554, display.count1478(actualInputList))
    }

    @Test
    fun `should map all digits`() {
        val display = DigitalDisplay()
        assertEquals(
            mapOf(
                0 to "abcdeg",
                1 to "ab",
                2 to "acdfg",
                3 to "abcdf",
                4 to "abef",
                5 to "bcdef",
                6 to "bcdefg",
                7 to "abd",
                8 to "abcdefg",
                9 to "abcdef"
            ), display.mapWires(singleInput.substringBefore(" | "))
        )
    }

    @Test
    fun `should read digits`() {
        val display = DigitalDisplay()
        assertEquals("5353", display.readCode(singleInput))
    }

    @Test
    fun `should sum codes`() {
        val display = DigitalDisplay()
        assertEquals(61229, input.sumOf { display.readCode(it).toInt() })
    }

    @Test
    fun `should sum actual codes`() {
        val display = DigitalDisplay()
        assertEquals(990964, actualInputList.sumOf { display.readCode(it).toInt() })
    }
}