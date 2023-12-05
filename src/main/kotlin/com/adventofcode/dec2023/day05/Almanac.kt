package com.adventofcode.dec2023.day05

class Almanac(input: List<String>) {

    val seeds: List<Long>

    val soils = ResourceMapper()
    val fertilizers = ResourceMapper()
    val waters = ResourceMapper()
    val lights = ResourceMapper()
    val temperatures = ResourceMapper()
    val humidities = ResourceMapper()
    val locations = ResourceMapper()

    init {
        var i = 0
        seeds = input[i].drop(7).split(" ").map { it.toLong() }
        i += 3
        i = populate(input, i, soils) + 2
        i = populate(input, i, fertilizers) + 2
        i = populate(input, i, waters) + 2
        i = populate(input, i, lights) + 2
        i = populate(input, i, temperatures) + 2
        i = populate(input, i, humidities) + 2
        populate(input, i, locations)
    }

    fun populate(input: List<String>, start: Int, map: ResourceMapper): Int {
        var i = start
        while (i in input.indices && input[i] != "") {
            val values = input[i].split(" ").map { it.toLong() }
            map.addRange(values[0], values[1], values[2])
            i++
        }
        return i
    }

    fun getLocationForSeed(seed: Long): Long {
        val soil = soils.map(seed)
        val fertilizer = fertilizers.map(soil)
        val water = waters.map(fertilizer)
        val light = lights.map(water)
        val temperature = temperatures.map(light)
        val humidity = humidities.map(temperature)
        return locations.map(humidity)
    }

    fun getSeedForLocation(location: Long): Long {
        val humidity = locations.revert(location)
        val temperature = humidities.revert(humidity)
        val light = temperatures.revert(temperature)
        val water = lights.revert(light)
        val fertilizer = waters.revert(water)
        val soil = fertilizers.revert(fertilizer)
        return soils.revert(soil)
    }
    fun getSoilForSeed(seed: Long): Long = soils.map(seed)

    fun getBestLocation(): Long = seeds.minOf { getLocationForSeed(it) }
    fun getBestLocationForSeedRanges(): Long {
        val seedRanges = seeds.asSequence().chunked(2).map { it[0]..<it[1] + it[0] }
        var i = 1L
        while(true) {
            val seed = getSeedForLocation(i)
            if (seedRanges.any { seed in it }) {
                return i
            }
            i++
        }
    }
}