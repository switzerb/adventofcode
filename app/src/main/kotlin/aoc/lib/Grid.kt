import aoc.lib.Vector
import kotlin.Int.Companion.MAX_VALUE

class Grid {
    var grid: List<Vector> = mutableListOf()

    /** dimensions: 0 = x, 1 = y, 2 = z **/
    private fun maxDimension(dimension: Int): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var max = 0
        grid.map {
            if (it[dimension] > max) {
                max = it[dimension]
            }
        }
        return max
    }

    private fun minDimension(dimension: Int): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var min = MAX_VALUE
        grid.map {
            if (it[dimension] < min) {
                min = it[dimension]
            }
        }
        return if (min == MAX_VALUE) 0 else min
    }

    fun print2D(): String {
        val maxX = maxDimension(0)
        val minX = minDimension(0)
        val maxY = maxDimension(1)
        val minY = minDimension(1)

        var string = StringBuilder()

        (minY..maxY).map { col ->
            (minX..maxX).map { row ->
                if (grid.contains(Vector(row, col))) {
                    string.append("#")
                } else {
                    string.append(".")
                }
            }
            string.append('\n')
        }
        return string.toString()
    }
}
