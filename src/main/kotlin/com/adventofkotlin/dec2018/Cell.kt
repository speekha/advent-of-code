package com.adventofkotlin.dec2018

enum class Cell(val code: String) {
    START("S"), END("X"), EMPTY("."), OBSTACLE("B"), VISITED("*");

    override fun toString() = code
}