package com.adventofcode.dec2017.day20

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class ParticleSimulatorTest {

    val input1 = listOf(
            listOf("p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>",
                    "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>"),
            listOf("p=< 4,0,0>, v=< 1,0,0>, a=<-1,0,0>",
                    "p=< 2,0,0>, v=<-2,0,0>, a=<-2,0,0>"),
            listOf("p=< 4,0,0>, v=< 0,0,0>, a=<-1,0,0>",
                    "p=<-2,0,0>, v=<-4,0,0>, a=<-2,0,0>"),
            listOf("p=< 3,0,0>, v=<-1,0,0>, a=<-1,0,0>",
                    "p=<-8,0,0>, v=<-6,0,0>, a=<-2,0,0>"))

    val input2 = listOf("p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>",
            "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>",
            "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>",
            "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>")

    val sizes = listOf(1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 992, 974, 957, 926, 901, 881, 858, 813, 780,
            775, 754, 754, 736, 726, 701, 686, 674, 671, 652, 648, 619, 607, 583, 574, 560, 549, 538, 532, 503, 477)

    @Test
    fun `should parse particles`() {
        val inputs = input1[0]
        assertEquals(Particle(
                Coordinates(3, 0, 0),
                Coordinates(2, 0, 0),
                Coordinates(-1, 0, 0)
        ), Particle.parse(inputs[0]))
        assertEquals(Particle(
                Coordinates(4, 0, 0),
                Coordinates(0, 0, 0),
                Coordinates(-2, 0, 0)
        ), Particle.parse(inputs[1]))
    }

    @Test
    fun `should compute distances`() {
        val inputs = input1[0]
        assertEquals(3, Particle.parse(inputs[0]).distance)
        assertEquals(4, Particle.parse(inputs[1]).distance)
    }

    @Test
    fun `should simulate next state`() {
        var particle0 = Particle.parse(input1[0][0])
        var particle1 = Particle.parse(input1[0][1])
        for (i in 1 until input1.size) {
            Assert.assertEquals("Step $i (0)", Particle.parse(input1[i][0]), particle0.computeNextPosition())
            Assert.assertEquals("Step $i (1)", Particle.parse(input1[i][1]), particle1.computeNextPosition())
            particle0 = particle0.computeNextPosition()
            particle1 = particle1.computeNextPosition()
        }
    }

    @Test
    fun `should simulate successives states`() {
        val particle0 = Particle.parse(input1[0][0])
        val particle1 = Particle.parse(input1[0][1])
        for (i in 1 until input1.size) {
            assertEquals("Step $i (0)", Particle.parse(input1[i][0]), particle0.computePosition(i))
            assertEquals("Step $i (1)", Particle.parse(input1[i][1]), particle1.computePosition(i))
        }
    }

    @Test
    fun `should find particle with biggest acceleration`() {
        assertEquals(0, ParticleSimulator(input1[0]).findParticleWithBiggestAcceleration())
    }

    @Test
    fun `have one particle left`() {
        with(ParticleSimulator(input2)) {
            for (i in 0 until 5) {
                tick()
            }
            assertEquals(1, particles.size)
        }
    }


    @Test
    fun `test real values`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day20/input.txt").readLines()
        with(ParticleSimulator(input)) {
            assertEquals(144, findParticleWithBiggestAcceleration())
            for (i in 0 until sizes.size) {
                tick()
                assertEquals(sizes[i], particles.size)
            }
        }
    }
}