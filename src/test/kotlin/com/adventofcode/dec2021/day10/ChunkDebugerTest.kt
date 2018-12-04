package com.adventofcode.dec2021.day10

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ChunkDebugerTest {

    val input = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    )

    @Test
    fun `should recognise valid chunks`() {
        val debuger = ChunkDebuger()
        debuger.assertChunk("()", true)
        debuger.assertChunk("([])", true)
        debuger.assertChunk("{()()()}", true)
        debuger.assertChunk("<([{}])>", true)
        debuger.assertChunk("[<>({}){}[([])<>]]", true)
        debuger.assertChunk("(((((((((())))))))))", true)
    }

    @Test
    fun `should recognise corrupt chunks`() {
        val debuger = ChunkDebuger()
        debuger.assertChunk("(]", false)
        debuger.assertChunk("{()()()>", false)
        debuger.assertChunk("(((()))}", false)
        debuger.assertChunk("<([]){()}[{}])", false)
    }

    private fun ChunkDebuger.assertChunk(chunk: String, valid: Boolean) {
        if (valid) {
            assertEquals("", completeLine(chunk), "Incorrect validity for $chunk")
        } else {
            try {
                completeLine(chunk)
                fail("$chunk should be corrupted")
            } catch (e: MismatchException) {
            }
        }
    }

    @Test
    fun `should score corrupt chunks`() {
        val debuger = ChunkDebuger()
        assertEquals(26397, debuger.scoreCorruptedLines(input))
    }

    @Test
    fun `should score actual corrupt chunks`() {
        val debuger = ChunkDebuger()
        assertEquals(358737, debuger.scoreCorruptedLines(actualInputList))
    }

    @Test
    fun `should complete truncated lines`() {
        val debuger = ChunkDebuger()
        assertEquals("}}]])})]", debuger.completeLine("[({(<(())[]>[[{[]{<()<>>"))
        assertEquals(")}>]})", debuger.completeLine("[(()[<>])]({[<{<<[]>>("))
    }

    @Test
    fun `should score incomplete chunks`() {
        val debuger = ChunkDebuger()
        assertEquals(294, debuger.scoreIncompleteLine("<{([{{}}[<[[[<>{}]]]>[]]"))
    }

    @Test
    fun `should find middle score`() {
        val debuger = ChunkDebuger()
        assertEquals(288957, debuger.findMiddleScore(input))
    }

    @Test
    fun `should find actual middle score`() {
        val debuger = ChunkDebuger()
        assertEquals(4329504793, debuger.findMiddleScore(actualInputList))
    }
}