package com.adventofcode.dec2016.day4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SectorAnalyzerTest {

    @Test
    fun `check room name`() {
        assertEquals("aaaaa-bbb-z-y-x", SectorAnalyzer().getRoomName("aaaaa-bbb-z-y-x-123[abxyz]"))
        assertEquals("a-b-c-d-e-f-g-h", SectorAnalyzer().getRoomName("a-b-c-d-e-f-g-h-987[abcde]"))
    }

    @Test
    fun `check sector ID`() {
        assertEquals("123", SectorAnalyzer().getSectorId("aaaaa-bbb-z-y-x-123[abxyz]"))
        assertEquals("987", SectorAnalyzer().getSectorId("a-b-c-d-e-f-g-h-987[abcde]"))
        assertEquals("404", SectorAnalyzer().getSectorId("not-a-real-room-404[oarel]"))
        assertEquals("200", SectorAnalyzer().getSectorId("totally-real-room-200[decoy]"))
    }

    @Test
    fun `read checksum`() {
        assertEquals("abxyz", SectorAnalyzer().getChecksum("aaaaa-bbb-z-y-x-123[abxyz]"))
        assertEquals("abcde", SectorAnalyzer().getChecksum("a-b-c-d-e-f-g-h-987[abcde]"))
    }

    @Test
    fun `compute checksum`() {
        assertEquals("abxyz", SectorAnalyzer().computeChecksum("aaaaa-bbb-z-y-x-123[abxyz]"))
        assertEquals("abcde", SectorAnalyzer().computeChecksum("a-b-c-d-e-f-g-h-987[abcde]"))
    }

    @Test
    fun `should be real rooms`() {
        assertTrue(SectorAnalyzer().isARealRoom("aaaaa-bbb-z-y-x-123[abxyz]"))
        assertTrue(SectorAnalyzer().isARealRoom("a-b-c-d-e-f-g-h-987[abcde]"))
        assertTrue(SectorAnalyzer().isARealRoom("not-a-real-room-404[oarel]"))
    }

    @Test
    fun `should not be real rooms`() {
        assertFalse(SectorAnalyzer().isARealRoom("totally-real-room-200[decoy]"))
    }
    @Test
    fun `add real rooms sector ids`() {
        val input = listOf("aaaaa-bbb-z-y-x-123[abxyz]",
                "a-b-c-d-e-f-g-h-987[abcde]",
                "not-a-real-room-404[oarel]",
                "totally-real-room-200[decoy]")
        assertEquals(1514, SectorAnalyzer().addRealRoomSectors(input))
    }

    @Test
    fun `decipher room name`() {
        assertEquals("very encrypted name", SectorAnalyzer().decipherRoomName("qzmt-zixmtkozy-ivhz", 343))
    }
}

