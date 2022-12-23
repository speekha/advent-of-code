package com.adventofcode.dec2022.day23

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Neighbor

enum class MoveOption(
    val direction: Direction,
    val neighbors: List<Neighbor>
    ) {
    NORTH(Direction.UP, listOf(Neighbor.TOP_LEFT, Neighbor.TOP, Neighbor.TOP_RIGHT)),
    SOUTH(Direction.DOWN, listOf(Neighbor.BOTTOM_LEFT, Neighbor.BOTTOM, Neighbor.BOTTOM_RIGHT)),
    WEST(Direction.LEFT, listOf(Neighbor.TOP_LEFT, Neighbor.LEFT, Neighbor.BOTTOM_LEFT)),
    EAST(Direction.RIGHT, listOf(Neighbor.TOP_RIGHT, Neighbor.RIGHT, Neighbor.BOTTOM_RIGHT))

}