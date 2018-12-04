package com.adventofcode.dec2018.day13

import com.adventofcode.dec2018.day13.Direction.*
import java.io.File

val NULL_POSITION = Position(-1, -1)

class CollisionAvoidance(input: List<String>) {
    val map = input.map {
        it.map {
            when (it) {
                '>', '<' -> '-'
                'v', '^' -> '|'
                else -> it
            }
        }
    }.toTypedArray()

    val carts: List<Cart>
    val state: String
        get() {
            val background = map.map { it.toTypedArray() }.toTypedArray()
            carts.forEach {
                background[it.position.y][it.position.x] = when (it.direction) {
                    UP -> '^'
                    DOWN -> 'v'
                    LEFT -> '<'
                    RIGHT -> '>'
                }
            }
            return background.joinToString("\n") { it.joinToString("") }
        }

    init {
        val list = mutableListOf<Cart>()
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                when (char) {
                    '>' -> Cart(list.size, Position(x, y), RIGHT)
                    '<' -> Cart(list.size, Position(x, y), LEFT)
                    '^' -> Cart(list.size, Position(x, y), UP)
                    'v' -> Cart(list.size, Position(x, y), DOWN)
                    else -> null
                }?.let { list += it }
            }
        }
        carts = list
    }

    fun iterate(cleanUp: Boolean = false) {
        carts.filter { it.position != NULL_POSITION }
                .forEach { cart ->
                    if (cart.position != NULL_POSITION) {
                        val nextPosition = cart.position + cart.direction
                        val crash = carts.firstOrNull { nextPosition == it.position }
                        if (crash != null) {
                            if (cleanUp) {
                                cart.position = NULL_POSITION
                                crash.position = NULL_POSITION
                            } else {
                                throw CartCrashException(nextPosition)
                            }
                        } else {
                            cart.position = nextPosition
                            if (map[cart.position.y][cart.position.x] == '+') {
                                cart.direction += cart.nextTurn.inc
                                cart.nextTurn++
                            } else {
                                cart.direction += map[cart.position.y][cart.position.x]
                            }
                        }
                    }
                }
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day13/input.txt").readLines()
    var avoider = CollisionAvoidance(input)
    try {
        while (true) {
            avoider.iterate()
        }
    } catch (e: CartCrashException) {
        println("Crash in ${e.position}")
    }

    avoider = CollisionAvoidance(input)
    while (avoider.carts.count { it.position != NULL_POSITION } > 1) {
        avoider.iterate(true)
    }
    println("Last cart in ${avoider.carts.first { it.position != NULL_POSITION }.position}")
}
