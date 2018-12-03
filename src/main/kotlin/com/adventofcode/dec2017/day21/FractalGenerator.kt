package com.adventofcode.dec2017.day21

class FractalGenerator(input: List<String>) {

    var pixels = arrayOf(
            booleanArrayOf(false, true, false),
            booleanArrayOf(false, false, true),
            booleanArrayOf(true, true, true)
    )

    val transformations = initTransformations(input)

    fun iterate() {
        when {
            pixels.size % 2 == 0 -> pixels = process(pixels, 2)
            pixels.size % 3 == 0 -> pixels = process(pixels, 3)
        }
    }

    private fun process(pixels: Array<BooleanArray>, size: Int): Array<BooleanArray> {
        val unit = pixels.size / size
        val result = Array(unit * (size + 1)) { BooleanArray(unit * (size + 1)) }
        (0 until unit).forEach { row ->
            (0 until unit).forEach { col ->
                val extract = extract(row * size, col * size, size)
                val replace = transformations[getKey(extract)]?.output ?: error("No matching pattern found")
                (0..size).forEach { i ->
                    (0..size).forEach { j ->
                        result[row * (size + 1) + i][col * (size + 1) + j] = replace[i][j]
                    }
                }
            }
        }
        return result
    }

    fun extract(startRow: Int, startCol: Int, size: Int): Array<BooleanArray> {
        return Array(size) { row ->
            BooleanArray(size) { col ->
                pixels[startRow + row][startCol + col]
            }
        }
    }

    companion object {

        fun parse(input: String) = input.split("/").map { it.map { it == '#' }.toBooleanArray() }.toTypedArray()

        fun getKey(array: Array<BooleanArray>) = array.joinToString("/") {
            it.joinToString("") { if (it) "#" else "." }
        }

        private fun initTransformations(input: List<String>) = input.asSequence()
                .map {
                    val (pattern, output) = it.split(" => ")
                    Pattern(pattern, parse(output))
                }
                .flatMap { pattern -> flipPattern(pattern) }
                .map { it.input to it }
                .associate { it }

        private fun flipPattern(pattern: Pattern): Sequence<Pattern> {
            val flipV = flipVertically(pattern.input)
            val flipH = flipHorizontally(pattern.input)
            val flipBoth = flipHorizontally(flipV)
            val flipD = flipDiagonally(pattern.input)
            return sequenceOf(pattern,
                    pattern.copy(input = flipV),
                    pattern.copy(input = flipH),
                    pattern.copy(input = flipD),
                    pattern.copy(input = flipHorizontally(flipD)),
                    pattern.copy(input = flipVertically(flipD)),
                    pattern.copy(input = flipBoth),
                    pattern.copy(input = flipDiagonally(flipBoth))
            )
        }

        fun flipHorizontally(input: String) = input.split("/").map { it.reversed() }.joinToString("/")

        fun flipVertically(input: String) = input.split("/").reversed().joinToString("/")

        fun flipDiagonally(input: String): String {
            val rows = input.split("/").map { it.toCharArray() }
            return Array(rows.size) { i -> CharArray(rows.size) { j -> rows[j][i] } }
                    .joinToString("/") { it.joinToString("") }
        }
    }

    fun sumActivePixels(): Int = pixels.sumBy { it.count { it } }

}