package com.adventofcode.dec2016.day22

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class StorageNodeTest {

    val input = listOf("root@ebhq-gridcenter# df -h",
            "Filesystem            Size  Used  Avail  Use%",
            "/dev/grid/node-x0-y0   10T    8T     2T   80%",
            "/dev/grid/node-x0-y1   11T    6T     5T   54%",
            "/dev/grid/node-x0-y2   32T   28T     4T   87%",
            "/dev/grid/node-x1-y0    9T    7T     2T   77%",
            "/dev/grid/node-x1-y1    8T    0T     8T    0%",
            "/dev/grid/node-x1-y2   11T    7T     4T   63%",
            "/dev/grid/node-x2-y0   10T    6T     4T   60%",
            "/dev/grid/node-x2-y1    9T    8T     1T   88%",
            "/dev/grid/node-x2-y2    9T    6T     3T   66%"
    )

    @Test
    fun `should parse input`() {
        val nodes = StorageNode.parse(input)
        assertEquals(listOf(
                StorageNode(x = 0, y = 0, used = 8, free = 2),
                StorageNode(x = 0, y = 1, used = 6, free = 5),
                StorageNode(x = 0, y = 2, used = 28, free = 4),
                StorageNode(x = 1, y = 0, used = 7, free = 2),
                StorageNode(x = 1, y = 1, used = 0, free = 8),
                StorageNode(x = 1, y = 2, used = 7, free = 4),
                StorageNode(x = 2, y = 0, used = 6, free = 4),
                StorageNode(x = 2, y = 1, used = 8, free = 1),
                StorageNode(x = 2, y = 2, used = 6, free = 3)
        ), nodes)
    }

    @Test
    fun `should count viable pairs`() {
        val nodes = StorageNode.parse(input)
        assertEquals(5, NodeHandler(nodes).countViablePairs())
    }

    @Test
    fun `should find actual viable pairs`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2016/day22/input.txt").readLines()
        val nodes = StorageNode.parse(input)
        assertEquals(864, NodeHandler(nodes).countViablePairs())
    }

    @Test
    fun `should move data and count steps`() {
        val nodes = StorageNode.parse(input)
        assertEquals(7, NodeHandler(nodes).accessData())
    }
}