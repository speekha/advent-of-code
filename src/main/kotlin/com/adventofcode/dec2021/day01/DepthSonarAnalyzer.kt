package com.adventofcode.dec2021.day01

class DepthSonarAnalyzer {

    fun countIncreases(depths: List<Int>) = depths.windowed(2).count { it[1] > it[0] }

    fun analyzeWindows(depths: List<Int>, window: Int): Int = countIncreases(depths.windowed(window).map { it.sum() })
}
