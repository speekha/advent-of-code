package com.adventofcode.dec2021.day13

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PageFolderTest {

    private val dots = listOf(
        "6,10",
        "0,14",
        "9,10",
        "0,3",
        "10,4",
        "4,11",
        "6,0",
        "6,12",
        "4,1",
        "0,13",
        "10,12",
        "3,4",
        "3,0",
        "8,4",
        "1,10",
        "2,14",
        "8,10",
        "9,0"
    )

    private val folds = listOf(
        "fold along y=7",
        "fold along x=5"
    )

    @Test
    fun `should map dots`() {
        val result = "   #  #  # \n" +
                "    #      \n" +
                "           \n" +
                "#          \n" +
                "   #    # #\n" +
                "           \n" +
                "           \n" +
                "           \n" +
                "           \n" +
                "           \n" +
                " #    # ## \n" +
                "    #      \n" +
                "      #   #\n" +
                "#          \n" +
                "# #        "
        val folder = PageFolder(dots + "")
        assertEquals(result, folder.printPage())
    }

    @Test
    fun `should map dots and fold horizontally`() {
        val result = "# ##  #  # \n" +
                "#   #      \n" +
                "      #   #\n" +
                "#   #      \n" +
                " # #  # ###\n" +
                "           \n" +
                "           "
        val folder = PageFolder(dots + "" + folds[0])
        folder.fold(folds[0])
        assertEquals(result, folder.printPage())
        assertEquals(17, folder.countDots())
    }

    @Test
    fun `should map dots and fold vertically`() {
        val result = "#####\n" +
                "#   #\n" +
                "#   #\n" +
                "#   #\n" +
                "#####\n" +
                "     \n" +
                "     "
        val folder = PageFolder(dots + "" + folds)
        folder.foldAll()
        assertEquals(result, folder.printPage())
        assertEquals(16, folder.countDots())
    }

    @Test
    fun `should map actual dots and fold completely`() {
        val folder = PageFolder(actualInputList)
        folder.fold(folder.folds[0])
        assertEquals(682, folder.countDots())
    }

    @Test
    fun `should map actual dots and fold once`() {
        val folder = PageFolder(actualInputList)
        folder.foldAll()
        println(folder.printPage())
        assertEquals(104, folder.countDots())
    }
}