package com.adventofcode.dec2018.day8

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LicenceReaderTest {

    val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"

    @Test
    fun `should parse nodes`() {
        val reader = LicenceReader(input)
        Assertions.assertEquals(138, reader.sumMetadata())
    }

    @Test
    fun `should parse actual nodes`() {
        val input = readInputAsList()[0]
        val reader = LicenceReader(input)
        Assertions.assertEquals(37439, reader.sumMetadata())
    }

    @Test
    fun `should find root node value`() {
        val reader = LicenceReader(input)
        Assertions.assertEquals(66, reader.computeRootNodeValue())
    }

    @Test
    fun `should find actual root node value`() {
        val input = readInputAsList()[0]
        val reader = LicenceReader(input)
        Assertions.assertEquals(20815, reader.computeRootNodeValue())
    }
}