package com.adventofcode.dec2020.day03

class TobogganNavigator {
    fun countTrees(map: List<String>, right: Int, down: Int): Int {
        var count = 0
        var pos = 0
        for(i in map.indices step down) {
            if (map[i][pos] == '#') {
                count++
            }
            pos = (pos + right) % map[i].length
        }
        return count
    }
}