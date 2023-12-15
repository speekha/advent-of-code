package com.adventofcode.dec2023.day15

import com.adventofcode.actualInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HashTest {

    val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

    @Test
    fun `should hash a string`() {
        val hash = Hash()
        assertEquals(30, hash.hash("rn=1"))
    }

    @Test
    fun `should add hashes`() {
        val hash = Hash()
        assertEquals(1320, hash.hashSteps(input))
    }
    @Test
    fun `should add actual hashes`() {
        val hash = Hash()
        assertEquals(516804, hash.hashSteps(actualInput))
    }

    @Test
    fun `should adjust lenses`() {
        val hash = Hash()
        assertEquals(145, hash.adjust(input))
    }
    @Test
    fun `should adjust actual lenses`() {
        val hash = Hash()
        assertEquals(231844, hash.adjust(actualInput))
    }
}