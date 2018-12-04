package com.adventofcode.dec2020.day06

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FormProcessorTest {

    val input = listOf(
        "abc",
        "",
        "a",
        "b",
        "c",
        "",
        "ab",
        "ac",
        "",
        "a",
        "a",
        "a",
        "a",
        "",
        "b"
    )

    @Test
    fun `should split questions in groups`() {
        val processor = FormProcessor(input)
        Assertions.assertEquals(5, processor.data.size)
    }

    @Test
    fun `should count questions`() {
        val processor = FormProcessor(input)
        Assertions.assertEquals(11, processor.countQuestions())
    }

    @Test
    fun `should count questions in actual data`() {
        val processor = FormProcessor(readInputAsList())
        Assertions.assertEquals(6775, processor.countQuestions())
    }

    @Test
    fun `should count common questions`() {
        val processor = FormProcessor(input)
        Assertions.assertEquals(6, processor.countCommonQuestions())
    }

    @Test
    fun `should count common questions in actual data`() {
        val processor = FormProcessor(readInputAsList())
        Assertions.assertEquals(3356, processor.countCommonQuestions())
    }
}