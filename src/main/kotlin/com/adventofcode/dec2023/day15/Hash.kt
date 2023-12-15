package com.adventofcode.dec2023.day15

class Hash {
    fun hash(s: String): Int = s.fold(0) { acc, value -> ((acc + value.code) * 17) % 256 }

    fun hashSteps(input: String): Int = input.split(",").sumOf { hash(it) }

    val lenses = mutableMapOf<String, Int>()

    fun adjust(input: String): Int {
        val boxes = Array(256) { mutableListOf<String>() }
        input.split(",").forEach { step ->
            val lens = parseLens(step)
            when {
                '=' in step -> {
                    val box = hash(lens)
                    if (lens !in boxes[box]) {
                        boxes[box] += lens
                    }
                }

                '-' in step -> boxes[hash(lens)].remove(lens)
            }
        }
        return boxes.indices.sumOf { i ->
            boxes[i].withIndex().sumOf { (index, lens) ->
                (i + 1) * (index + 1) * (lenses[lens] ?: 0)
            }
        }
    }

    private fun parseLens(step: String): String = if ("=" in step) {
        val (lens, focalLength) = step.split("=")
        lenses[lens] = focalLength.toInt()
        lens
    } else {
        step.dropLast(1)
    }
}
