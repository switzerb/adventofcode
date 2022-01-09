package aoc.lib

class MutableStack<E> {

    private val elements = mutableListOf<E>()

    val size: Int
        get() = elements.size

    fun isEmpty() = elements.isEmpty()

    fun push(item: E) = elements.add(item)

    fun pop(): E {
        if (isEmpty()) {
            throw NoSuchElementException("Nothing to pop here.")
        }
        return elements.removeAt(elements.size - 1)
    }

    fun peek(): E? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}
