package com.adventofcode.dec2019.day11

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HullPaintingRobot(
        private val commands: Channel<Long>,
        private val camera: Channel<Long>
): CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    var position = Position(0, 0)

    var direction = Direction.UP

    val map = mutableMapOf<Position, Long>()

    fun setPanel(pos: Position, color: Long) {
        map[pos] = color
    }

    suspend fun start() = launch {
        while (!commands.isClosedForReceive) {
            camera.send(map.getOrDefault(position, 0))
            val paint = commands.receive()
            map[position] = paint
            val move = commands.receive()
            direction = if (move == 0L) direction.turnLeft() else direction.turnRight()
            position += direction
        }
    }

    fun countPaintedPanels() = map.count()

    fun paintRegistration() {
        val minX = map.keys.minByOrNull { it.x }?.x ?:error("Min X not found")
        val maxX = map.keys.maxByOrNull { it.x }?.x ?:error("Max X not found")
        val minY = map.keys.minByOrNull { it.y }?.y ?:error("Min Y not found")
        val maxY = map.keys.maxByOrNull { it.y }?.y ?:error("Max Y not found")
        for(y in minY..maxY) {
            for(x in minX..maxX) {
                print(if (map[Position(x, y)] == 1L) '#' else ' ')
            }
            println()
        }
    }
}
