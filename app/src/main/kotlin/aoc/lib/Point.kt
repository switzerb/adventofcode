package aoc.lib

data class Point(val x: Int, val y: Int) {

    companion object {
        val ORIGIN = Point(0, 0)
    }

    val r = y
    val c = x

    fun cardinalNeighbors(): Set<Point> =
        setOf(
            Point(x - 1, y),
            Point(x + 1, y),
            Point(x, y + 1),
            Point(x, y - 1)
        )

    fun allNeighbors(): Set<Point> =
        cardinalNeighbors() + setOf(
            Point(x - 1, y - 1),
            Point(x - 1, y + 1),
            Point(x + 1, y - 1),
            Point(x + 1, y + 1)
        )
}
