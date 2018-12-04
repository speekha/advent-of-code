package com.adventofcode.dec2019.day08

class ImageProcessor(
        input: String,
        private val width: Int,
        private val height: Int
) {
    val layers: List<String> = decodeImage(input)

    private fun decodeImage(input: String): List<String> = input.chunked(width * height)

    fun findTestLayer(): String = layers.minByOrNull { it.count { it == '0' } } ?: error("No layer found")

    fun calculateChecksum(): Int {
        val layer = findTestLayer()
        return layer.count { it == '1' } * layer.count { it == '2' }
    }

    fun render(): String = layers
            .fold("2".repeat(width * height)) { acc, layer ->
                acc.zip(layer) { a, b ->
                    a.takeIf { it != '2' } ?: b
                }.joinToString("")
            }
            .replace('0', ' ')
            .replace('1', '#')
            .chunked(width).joinToString("\n")
}
