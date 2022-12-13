package com.adventofcode.dec2022.day13

sealed class Element : Comparable<Element>

data class IntElement(val value: Int) : Element() {
    override fun compareTo(other: Element): Int {
        return when (other) {
            is IntElement -> this.value.compareTo(other.value)
            else -> ListElement(mutableListOf(this)).compareTo(other)
        }
    }

}

data class ListElement(
    val value: MutableList<Element> = mutableListOf()
) : Element() {

    constructor(vararg items: Element) : this(items.toMutableList())

    override fun compareTo(other: Element): Int = when (other) {
        is IntElement -> compareTo(ListElement(mutableListOf(other)))
        is ListElement -> compareList(other)
    }

    private fun compareList(other: ListElement): Int {
        var i = 0
        while (i in value.indices && i in other.value.indices) {
            val compareTo = value[i].compareTo(other.value[i])
            if (compareTo != 0) {
                return compareTo
            } else {
                i++
            }
        }
        return when {
            value.size == other.value.size -> 0
            value.size < other.value.size -> -1
            else -> 1
        }
    }
}