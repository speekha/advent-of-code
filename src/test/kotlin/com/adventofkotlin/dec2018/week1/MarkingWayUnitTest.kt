package com.adventofkotlin.dec2018.week1

import com.adventofkotlin.dec2018.MarkPath
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MarkingWayOnMapTests {

    fun addPath(input: String) = MarkPath(input).findShortestPath()

    @Test
    fun `Markes start and end as part of way`() {
        val mapString = """
        ....................
        .........XS.........
        ....................
        """.trimIndent()

        val marked = """
        ....................
        .........**.........
        ....................
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }

    @Test
    fun `Straight way is straight`() {
        val mapString = """
        ....................
        .....X..........S...
        ....................
        """.trimIndent()

        val marked = """
        ....................
        .....************...
        ....................
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }

    @Test
    fun `Use cross moves`() {
        val mapString = """
        ...........
        .......S...
        ...........
        ...........
        ...........
        ...........
        ..X........
        """.trimIndent()

        val marked = """
        ...........
        .......*...
        ......*....
        .....*.....
        ....*......
        ...*.......
        ..*........
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }

    @Test
    fun `Mark way around wall`() {
        val mapString = """
        ....................
        ......X...B.........
        ..........B.........
        ........BBB....S....
        ....................
        """.trimIndent()

        val marked = """
        ........***.........
        ......**..B*........
        ..........B.*.......
        ........BBB..***....
        ....................
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }

    @Test
    fun `Mark way around wall 2`() {
        val mapString = """
        ..........B.........
        ......X...B.........
        ..........B.........
        ........BBB....S....
        ....................
        """.trimIndent()

        val marked = """
        ..........B.........
        ......*...B.........
        ......*...B.........
        .......*BBB*****....
        ........***.........
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }

    @Test
    fun `Mark way on labirynth`() {
        val mapString = """
        BB..B...B...BBBBB...
        ....B.X.BBB.B...B.B.
        ..BBB.B.B.B.B.B.B.B.
        ....B.BBB.B.B.BS..B.
        BBB.B...B.B.BBBBBBB.
        ..B...B.............
        """.trimIndent()

        val marked = """
        BB..B...B...BBBBB.*.
        ....B.*.BBB.B...B*B*
        ..BBB*B.B.B.B.B.B*B*
        ....B*BBB.B.B.B**.B*
        BBB.B.**B.B.BBBBBBB*
        ..B...B.***********.
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }

    @Test
    fun `Navigate on forest`() {
        val mapString = """
..B....B..B.....BB.BBB......B..B.......B....BBB.....BB.B....BB.BB..BB.......BBB...BBBBB.....B...B..B
....BB.....B.B.B....BBB..........BBB.B....BB..B.B..B...BBBBBBBB..BB.B..........B..B.......B.BB..B..B
B.......S.B.B.....B.........B.B..B...B...BB.BB.B...B.B.B...B..BB.BB.....BB..B........BB.B..B......BB
.......B..BBBB.B.....B....B...B.B..B...B...BB.B.BBB..BBB....B......B....B..B.B.BB......BB....BB....B
......B.BB.B.B......B..BBBB.B..BBBBB..B.B.BB.BBBBBB.BB.B..B.BBB..B......B......B....B........B.B..B.
B.....B......BB..BBB..B.B....BB.BBBBB.....BB..B..B..BB..BB..BB.BB......BB....B.BB.B...B..B......B...
B.B.B.................BB.B.B....BB.....BB.BB...BB.BB..B.B.......B......BB...BB.BB...BB...BB..B...B..
BB...BB.......BBBB........BBB..BBB.B.B...B..B....B...BBB..........B..B..B..BB..BB...BB..B...BBBBB..B
....B..B....B...B.B.B...B.BB........B....BBB..........B...B.BB.B..BB..B.......BB...B.B..B...B..B....
.........B.....BB.B.....B...BB.B.B.BBBB....B.....B.......BBBBB....B.B............BB.....B........B..
.B..B.B.B...............B..B.B.....B.BB.BB.B...B.BBB....BB.......BB...B.B.BBBB......B...B.....B.....
.B..B.....B.B..BB....BB.......B.B.BBB.B.....B..BB.B...B.....B..B...B..B.B..B.BB......B..B..B.....B.B
.......B.BBB.BB.......B.BBB....B...BB...B..........B.......B...B.....BBBB.B....B..B..BB.B.B.B...B.B.
B.B..BB.........BB.......BB.....B.BB..BB..BBB..B...BB....B.BBB..B....B.B....B.BBBB..B..BB.B.........
.B..BB......B...B..BB..BBB.B.B.B.BB.B.B..BBB.BBBB.B..B......BB.....BB.....B....BB....B..BB..........
B.B..B...B.B.B..B.B..B......B.B.B.BBB.B.B.B....B........BB..B....BB....BBB.BB.B....B...B....BBB.....
.B..BB...B.....B.B.B.BBB.B.........B...B..B.B...BBBBB....B.BBB..BB...........B.....BBBB....B.....BB.
.B......BB...BB.......B..B.B....B.BBB...BB.B......B.BBB..B....BB....BB....B.........B.B.B...BB....B.
B..B...B...B......B.....B......B..B..B....B.B.B.........B.BBB.B..B..BB..B..BB..B.....B..BB.BB.B....B
B.BB...B..BB..B.BB.B........B.BB......B...B.B..BB.BB.B.B......B...B..B.B...BBB...BBB.........X..B..B
.B.B.B.....B.BB..BB...BB.....B.BB.....B...BB...BBB..B..BBB..B.B.....B......B.....BB.....B..B.B..B...
BB...B.B..BB..B.B......B.....B....BBB...BBB.B...B.....B..BBBB.B..B..BBBBBB....BB.BB.B....BB.BB.BBB..
..B....B..BB........B..BB..BB..B...B.B..BB.B....BBB..B.....B......B...........BBBB.......B.BB....B..
BB....BBB...B.B...B.....B.B..B.B....B.B.B...B.B..BBBBBB.B....B...BB..BBB...B.BB....B.........BB..B..
        """.trimIndent()

        val marked = """
..B....B..B.....BB.BBB......B..B.......B....BBB.....BB.B....BB.BB..BB.......BBB...BBBBB.....B...B..B
....BB.....B.B.B....BBB..........BBB.B....BB..B.B..B...BBBBBBBB..BB.B..........B..B.......B.BB..B..B
B.......*.B.B.....B.........B.B..B...B...BB.BB.B...B.B.B...B..BB.BB.....BB..B........BB.B..B......BB
.......B.*BBBB.B.....B....B...B.B..B...B...BB.B.BBB..BBB....B......B....B..B.B.BB......BB....BB....B
......B.BB*B.B......B..BBBB.B..BBBBB..B.B.BB.BBBBBB.BB.B..B.BBB..B......B......B....B........B.B..B.
B.....B....**BB..BBB..B.B....BB.BBBBB.....BB..B..B..BB..BB..BB.BB......BB....B.BB.B...B..B......B...
B.B.B........*********BB.B.B....BB.....BB.BB...BB.BB..B.B.......B......BB...BB.BB...BB...BB..B...B..
BB...BB.......BBBB....***.BBB..BBB.B*B...B..B....B...BBB..........B..B..B..BB..BB...BB..B...BBBBB..B
....B..B....B...B.B.B...B*BB********B****BBB..........B...B.BB.B..BB..B.......BB...B.B..B...B..B....
.........B.....BB.B.....B.**BB.B.B.BBBB..*.B.....B.......BBBBB....B.B............BB.....B........B..
.B..B.B.B...............B..B.B.....B.BB.BB*B...B.BBB....BB.......BB...B.B.BBBB......B...B.....B.....
.B..B.....B.B..BB....BB.......B.B.BBB.B....*B..BB.B***B....*B..B...B..B.B..B.BB......B..B..B.....B.B
.......B.BBB.BB.......B.BBB....B...BB...B...*******B..*****B***B.....BBBB.B....B..B..BB.B.B.B...B.B.
B.B..BB.........BB.......BB.....B.BB..BB..BBB..B...BB....B.BBB.*B....B.B....B.BBBB..B..BB.B.........
.B..BB......B...B..BB..BBB.B.B.B.BB.B.B..BBB.BBBB.B..B......BB..***BB.....B....BB....B..BB..........
B.B..B...B.B.B..B.B..B......B.B.B.BBB.B.B.B....B........BB..B....BB****BBB.BB.B....B...B....BBB.....
.B..BB...B.....B.B.B.BBB.B.........B...B..B.B...BBBBB....B.BBB..BB.....******B.....BBBB....B.....BB.
.B......BB...BB.......B..B.B....B.BBB...BB.B......B.BBB..B....BB....BB....B..*******B.B.B...BB....B.
B..B...B...B......B.....B......B..B..B....B.B.B.........B.BBB.B..B..BB..B..BB..B....*B..BB.BB.B....B
B.BB...B..BB..B.BB.B........B.BB......B...B.B..BB.BB.B.B......B...B..B.B...BBB...BBB.*********..B..B
.B.B.B.....B.BB..BB...BB.....B.BB.....B...BB...BBB..B..BBB..B.B.....B......B.....BB.....B..B.B..B...
BB...B.B..BB..B.B......B.....B....BBB...BBB.B...B.....B..BBBB.B..B..BBBBBB....BB.BB.B....BB.BB.BBB..
..B....B..BB........B..BB..BB..B...B.B..BB.B....BBB..B.....B......B...........BBBB.......B.BB....B..
BB....BBB...B.B...B.....B.B..B.B....B.B.B...B.B..BBBBBB.B....B...BB..BBB...B.BB....B.........BB..B..
        """.trimIndent()

        assertEquals(marked, addPath(mapString))
    }
}