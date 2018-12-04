package com.adventofcode.dec2019.day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

class ThrustOptimizerTest {

    companion object {
        @JvmStatic
        fun dataWithoutFeedback() = Stream.of(
                Arguments.of(
                        "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0",
                        longArrayOf(4L, 3L, 2L, 1L, 0L),
                        43210L
                ),
                Arguments.of(
                        "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0",
                        longArrayOf(0L, 1L, 2L, 3L, 4L),
                        54321L
                ),
                Arguments.of(
                        "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0",
                        longArrayOf(1L, 0L, 4L, 3L, 2L),
                        65210L
                )
        )

        @JvmStatic
        fun dataWithFeedback() = Stream.of(
                Arguments.of("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28," +
                        "1005,28,6,99,0,0,5", longArrayOf(9L, 8L, 7L, 6L, 5L), 139629729L),
                Arguments.of("3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54," +
                        "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4," +
                        "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10", longArrayOf(9L, 7L, 8L, 5L, 6L), 18216L)
        )

    }

    @ParameterizedTest
    @MethodSource("dataWithoutFeedback")
    fun `should run amplifier code`(input: String, phases: LongArray, result: Long) {
        val output = ThrustOptimizer(input).computeOutput(phases)
        assertEquals(result, output)
    }

    @ParameterizedTest
    @MethodSource("dataWithoutFeedback")
    fun `should maximize output for code without feeback loop`(input: String, phases: LongArray, result: Long) {
        val output = ThrustOptimizer(input).maximizeOutput(false)
        assertEquals(result, output)
    }

    @Test
    fun `should maximize output for actual program`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day07/input.txt").readLines()
        val output = ThrustOptimizer(input[0]).maximizeOutput(false)
        assertEquals(366376, output)
    }

    @ParameterizedTest
    @MethodSource("dataWithFeedback")
    fun `should run amplifier code with feedback loop`(input: String, phases: LongArray, result: Long) {
        val output = ThrustOptimizer(input).computeOutput(phases)
        assertEquals(result, output)
    }

    @ParameterizedTest
    @MethodSource("dataWithFeedback")
    fun `should maximize output for code with feeback loop`(input: String, phases: LongArray, result: Long) {
        val output = ThrustOptimizer(input).maximizeOutput(true)
        assertEquals(result, output)
    }

    @Test
    fun `should maximize output for actual program with feedback loop`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day07/input.txt").readLines()
        val output = ThrustOptimizer(input[0]).maximizeOutput(true)
        assertEquals(21596786, output)
    }


}