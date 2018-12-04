package com.adventofcode.positioning

enum class Direction(override val x: Int, override val y: Int) : Orientation {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    operator fun plus(steps: Int) = values()[(ordinal + steps) % values().size]

    operator fun minus(steps: Int) = values()[(ordinal - steps + values().size) % values().size]

    operator fun inc() = turnRight()

    operator fun dec() = turnLeft()

    fun turnRight(): Direction = values()[(ordinal + 1) % values().size]

    fun turnLeft(): Direction = values()[(values().size + ordinal - 1) % values().size]
}

enum class Neighbor(vararg directions: Direction) : Orientation {
    TOP(Direction.UP),
    TOP_RIGHT(Direction.UP, Direction.RIGHT),
    RIGHT(Direction.RIGHT),
    BOTTOM_RIGHT(Direction.DOWN, Direction.RIGHT),
    BOTTOM(Direction.DOWN),
    BOTTOM_LEFT(Direction.DOWN, Direction.LEFT),
    LEFT(Direction.LEFT),
    TOP_LEFT(Direction.UP, Direction.LEFT);

    override val x: Int = directions.sumOf { it.x }
    override val y: Int = directions.sumOf { it.y }
}

enum class CardinalDirection(override val x: Int, override val y: Int) : Orientation {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    operator fun inc() = turnRight()

    operator fun dec() = turnLeft()

    fun turnRight(): CardinalDirection = values()[(ordinal + 1) % values().size]

    fun turnLeft(): CardinalDirection = values()[(values().size + ordinal - 1) % values().size]
}
