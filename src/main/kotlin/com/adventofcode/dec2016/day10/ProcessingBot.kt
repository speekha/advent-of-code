package com.adventofcode.dec2016.day10

data class ProcessingBot(

        val id: Int,

        val inputs: MutableSet<Int> = mutableSetOf(),

        var highOutput: Int? = null,
        var highOutputType: OutputType? = null,

        var lowOutput: Int? = null,
        var lowOutputType: OutputType? = null
)