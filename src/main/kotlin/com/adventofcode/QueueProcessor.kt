package com.adventofcode

import java.util.*

abstract class QueueProcessor<T : Any> {

    abstract val queue: Queue<T>

    fun process(computeNext: (T) -> Iterable<T>) {
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            queue.addAll(computeNext(current))
        }
    }

    fun prune(item: T) {
        queue.remove(item)
    }
}

class NonPrioritizedQueueProcessor<T : Any>(
    initial: List<T>
) : QueueProcessor<T>(){

    constructor(initial: T) : this(listOf(initial))

    override val queue = LinkedList<T>().apply {
        addAll(initial)
    }

}

class PrioritizedQueueProcessor<T : Comparable<T>>(
    initial: List<T>
): QueueProcessor<T>() {

    constructor(initial: T) : this(listOf(initial))

    override val queue = PriorityQueue<T>().apply {
        addAll(initial)
    }

}