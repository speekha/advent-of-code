package com.adventofcode.dec2021.day06

data class LanternFish(
    var countDown: Int
) {
    fun advanceDate() {
        countDown--
        if (countDown < 0) {
            countDown = 6
        }
    }
}
