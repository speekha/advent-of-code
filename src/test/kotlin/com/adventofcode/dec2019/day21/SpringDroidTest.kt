package com.adventofcode.dec2019.day21

import com.adventofcode.dec2019.intCode.IntCodeRunner
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class SpringDroidTest {
    @Test
    fun `should walk and jump over holes`() = testProgram(
            // !(A & B & C) & D
            "NOT T T\n" +
                    "AND A T\n" +
                    "AND B T\n" +
                    "AND C T\n" +
                    "NOT T J\n" +
                    "AND D J\n" +
                    "WALK\n"
    )

    @Test
    @Disabled
    fun `should run and jump over holes`() = testProgram(
            "NOT J J\n" +
                    "AND D J\n" +
                    "AND E J\n" +
                    "RUN\n"
    )

    fun testProgram(springScript: String) = runBlocking {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day21/input.txt").readLines()
        val commands = Channel<Char>(springScript.length)
        val output = Channel<Long>(100)
        val runner = IntCodeRunner(program[0], commands, output, { it.toLong() }, { it })
        runner.executeProgram()
        springScript.forEach { commands.send(it) }
        var resultPresent = false
        while (!output.isClosedForReceive) {
            val number = output.receive()
            if (number < 255) {
                print(number.toChar())
            } else {
                resultPresent = true
                assertEquals(19353692, number)
            }
        }
        assertTrue(resultPresent)
    }

}