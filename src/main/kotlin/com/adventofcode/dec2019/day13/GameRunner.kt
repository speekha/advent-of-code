package com.adventofcode.dec2019.day13

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.receiveOrNull

class GameRunner(
        private val display: Channel<Long>,
        private val joystick: Channel<Long>
) {

    val tiles = mutableMapOf<Position, Long>()

    val blockTiles: Int
        get() = tiles.count { it.value == 2L }

    var paddle: Position = Position(0, 0)

    var ball: Position = Position(0, 0)

    var score: Long = 0L

    suspend fun start() {
        while (!display.isClosedForReceive) {
            val x = display.receiveCatching().getOrNull()
            val y = display.receiveCatching().getOrNull()
            val type = display.receiveCatching().getOrNull()
            if (x != null && y != null && type != null) {
                val position = Position(x, y)
                when (type) {
                    3L -> paddle = position
                    4L -> {
                        ball = position
                        joystick.send(ball.x.compareTo(paddle.x).toLong())
                    }
                }
                if (type in 1L..4L) {
                    tiles[position] = type
                } else {
                    score = type
                }
            }
        }
    }


}
