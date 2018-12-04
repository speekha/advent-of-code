package com.adventofcode.dec2018.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


class MarbleGamesTest() {

    companion object {
        @JvmStatic
        fun data() = (0..25).map { arrayOf(it as Any) }
    }

    val games = listOf("[-] (0)",
            "[1]  0 (1)",
            "[2]  0 (2) 1 ",
            "[3]  0  2  1 (3)",
            "[4]  0 (4) 2  1  3 ",
            "[5]  0  4  2 (5) 1  3 ",
            "[6]  0  4  2  5  1 (6) 3 ",
            "[7]  0  4  2  5  1  6  3 (7)",
            "[8]  0 (8) 4  2  5  1  6  3  7 ",
            "[9]  0  8  4 (9) 2  5  1  6  3  7 ",
            "[1]  0  8  4  9  2(10) 5  1  6  3  7 ",
            "[2]  0  8  4  9  2 10  5(11) 1  6  3  7 ",
            "[3]  0  8  4  9  2 10  5 11  1(12) 6  3  7 ",
            "[4]  0  8  4  9  2 10  5 11  1 12  6(13) 3  7 ",
            "[5]  0  8  4  9  2 10  5 11  1 12  6 13  3(14) 7 ",
            "[6]  0  8  4  9  2 10  5 11  1 12  6 13  3 14  7(15)",
            "[7]  0(16) 8  4  9  2 10  5 11  1 12  6 13  3 14  7 15 ",
            "[8]  0 16  8(17) 4  9  2 10  5 11  1 12  6 13  3 14  7 15 ",
            "[9]  0 16  8 17  4(18) 9  2 10  5 11  1 12  6 13  3 14  7 15 ",
            "[1]  0 16  8 17  4 18  9(19) 2 10  5 11  1 12  6 13  3 14  7 15 ",
            "[2]  0 16  8 17  4 18  9 19  2(20)10  5 11  1 12  6 13  3 14  7 15 ",
            "[3]  0 16  8 17  4 18  9 19  2 20 10(21) 5 11  1 12  6 13  3 14  7 15 ",
            "[4]  0 16  8 17  4 18  9 19  2 20 10 21  5(22)11  1 12  6 13  3 14  7 15 ",
            "[5]  0 16  8 17  4 18(19) 2 20 10 21  5 22 11  1 12  6 13  3 14  7 15 ",
            "[6]  0 16  8 17  4 18 19  2(24)20 10 21  5 22 11  1 12  6 13  3 14  7 15 ",
            "[7]  0 16  8 17  4 18 19  2 24 20(25)10 21  5 22 11  1 12  6 13  3 14  7 15 ")

    @ParameterizedTest
    @MethodSource("data")
    fun `should compute state`(finalRound: Int) {
        val game = MarbleGame(9)
        repeat(finalRound) {
            game.play()
        }
        Assertions.assertEquals(games[finalRound], game.formatGame())
    }

}