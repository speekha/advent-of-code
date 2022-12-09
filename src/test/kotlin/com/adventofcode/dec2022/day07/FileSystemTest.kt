package com.adventofcode.dec2022.day07

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FileSystemTest {

    val input = listOf(
        "\$ cd /",
        "\$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "\$ cd a",
        "\$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "\$ cd e",
        "\$ ls",
        "584 i",
        "\$ cd ..",
        "\$ cd ..",
        "\$ cd d",
        "\$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k"
    )

    @Test
    fun `should traverse file system`() {
        val expected = Dir(
            "/", mutableSetOf(
                Dir(
                    "a", mutableSetOf(
                        Dir(
                            "e", mutableSetOf(
                                File("i", 584)
                            )
                        ),
                        File("f", 29116),
                        File("g", 2557),
                        File("h.lst", 62596)
                    )
                ),
                File("b.txt", 14848514),
                File("c.dat", 8504156),
                Dir(
                    "d", mutableSetOf(
                        File("j", 4060174),
                        File("d.log", 8033020),
                        File("d.ext", 5626152),
                        File("k", 7214296)
                    )
                )
            )
        )
        val fs = FileSystem()
        fs.parseOutput(input)
        assertEquals(expected, fs.root)
    }

    @Test
    fun `should find eligible directories`() {
        val fs = FileSystem()
        fs.parseOutput(input)
        assertEquals(95437, fs.sumEligibleDirs())
    }

    @Test
    fun `should find actually eligible directories`() {
        val fs = FileSystem()
        fs.parseOutput(actualInputList)
        assertEquals(1077191, fs.sumEligibleDirs())
    }

    @Test
    fun `should find directory to delete`() {
        val fs = FileSystem()
        fs.parseOutput(input)
        assertEquals(24933642, fs.freeUpSpace())
    }

    @Test
    fun `should find actual directory to delete`() {
        val fs = FileSystem()
        fs.parseOutput(actualInputList)
        assertEquals(5649896, fs.freeUpSpace())
    }
}