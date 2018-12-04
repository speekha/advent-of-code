package com.adventofcode.dec2021.day05

class VentDetector(input: List<String>) {
    val vents: List<VentLine> = input.map { data ->
        val (start, end) = data.split(" -> ")
        val (x1, y1) = start.split((",")).map { it.toInt() }
        val (x2, y2) = end.split((",")).map { it.toInt() }
        VentLine(Point(x1, y1), Point(x2, y2))
    }

    fun countIntersections(): Int {
        val xMax = vents.maxOf { it.start.x }
        val yMax = vents.maxOf { it.start.y }
        val map = Array(xMax + 1) { IntArray(yMax + 1) }
        vents.forEach { vent ->
            when {
                vent.start.x == vent.end.x -> foreach(vent.start.y, vent.end.y) { y ->
                    map[vent.start.x][y]++
                }
                vent.start.y == vent.end.y -> foreach(vent.start.x, vent.end.x) { x ->
                    map[x][vent.start.y]++
                }
            }
        }
        return map.sumOf { it.count { it > 1 } }
    }

    fun foreach(a: Int, b: Int, block: (Int) -> Unit) {
        val range = if (a <= b) a..b else b..a
        for (i in range) {
            block(i)
        }
    }

    fun countAllIntersections(): Int {
        val xMax = vents.maxOf { it.start.x }
        val yMax = vents.maxOf { it.start.y }
        val map = Array(xMax + 2) { IntArray(yMax + 2) }
        vents.forEach { vent ->
            var point = vent.start
            val end = vent.end
            val increment = computeStep(point, end)
            do {
                map[point]++
                point += increment
            } while (point != end)
            map[end]++
        }
        return map.sumOf { it.count { it > 1 } }
    }

    private fun computeStep(a: Point, b: Point): Point = Point(b.x.compareTo(a.x), b.y.compareTo(a.y))
}

private operator fun Array<IntArray>.set(point: Point, value: Int) {
    this[point.x][point.y] = value
}

private operator fun Array<IntArray>.get(point: Point) = this[point.x][point.y]
