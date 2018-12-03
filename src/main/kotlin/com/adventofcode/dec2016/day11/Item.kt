package com.adventofcode.dec2016.day11

data class Item(val isotope: String, val type: ItemType) {
    override fun toString(): String {
        return "${isotope[0].toUpperCase()}${type.toString()[0].toUpperCase()}"
    }
}