package com.adventofcode.dec2019.day12

import com.adventofcode.lcm
import kotlin.math.abs
import kotlin.reflect.KProperty1

class MoonTracker(input: List<String>) {

    var moons: List<Moon> = parseInput(input)

    private fun parseInput(input: List<String>): List<Moon> = input.map { initMoon(it) }

    private fun initMoon(input: String): Moon {
        val (x, y, z) = input.drop(1).dropLast(1).split(", ").map { it.drop(2).toInt() }
        return Moon(x, y, z, 0, 0, 0)
    }

    fun iterate() {
        moons = moons.map {
            val (x, vX) = it.iterate(Moon::x, Moon::vX)
            val (y, vY) = it.iterate(Moon::y, Moon::vY)
            val (z, vZ) = it.iterate(Moon::z, Moon::vZ)
            Moon(x, y, z, vX, vY, vZ)
        }
    }

    fun computeTotalEnergy(): Int {
        return moons.sumBy { computeEneergy(it) }
    }

    private fun computeEneergy(moon: Moon): Int {
        val potentialEnergy = abs(moon.x) + abs(moon.y) + abs(moon.z)
        val kineticEnergy = abs(moon.vX) + abs(moon.vY) + abs(moon.vZ)
        return potentialEnergy * kineticEnergy
    }

    fun findLoopLength(): Long {
        val loopX = findLoop(Moon::x, Moon::vX)
        val loopY = findLoop(Moon::y, Moon::vY)
        val loopZ = findLoop(Moon::z, Moon::vZ)
        return lcm(loopX.toLong(), lcm(loopY.toLong(), loopZ.toLong()))
    }

    private fun findLoop(pos: KProperty1<Moon, Int>, velocity: KProperty1<Moon, Int>): Int {
        val states = mutableMapOf<String, Int>()
        var step = 0
        var key = computeKey(pos, velocity)
        while (states[key] == null) {
            states[key] = step
            iterate()
            step++
            key = computeKey(pos, velocity)
        }
        return step - states[key]!!

    }

    fun Moon.iterate(pos: KProperty1<Moon, Int>, velocity: KProperty1<Moon, Int>): Pair<Int, Int> {
        val delta = velocity(this) + moons.sumBy { pos.get(it).compareTo(pos.get(this)) }
        return (pos.get(this) + delta) to delta
    }

    private fun computeKey(pos: KProperty1<Moon, Int>, velocity: KProperty1<Moon, Int>) = moons.joinToString("/") { "${pos.get(it)}-${velocity.get(it)}" }
}
