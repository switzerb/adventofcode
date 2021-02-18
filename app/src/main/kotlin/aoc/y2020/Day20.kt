package aoc.y2020

import aoc.lib.Resources.fileAsString

data class Tile(val id: Int, val s: String) {

    private val pixels: CharArray = s
        .filter { it == '.' || it == '#' }
        .toCharArray()
    private val width: Int = 10

    private val borders: List<String> = Orientation.values().map { getBorder(it) }
    private val flipped: List<String> = borders.map { it.reversed() }
    val edges = borders + flipped

    init {
        assert(pixels.size == width * width) { "Incorrect size of string input for image." }
    }

    fun getBorder(dir: Orientation): String =
        when (dir) {
            Orientation.Top -> pixels.take(width).joinToString("")
            Orientation.Bottom -> pixels.takeLast(width).joinToString("")
            Orientation.Left -> pixels.filterIndexed { idx,_ -> idx % width == 0 }.joinToString("")
            Orientation.Right -> pixels.filterIndexed { idx,_ -> (idx % width) == 9 }.joinToString("")
        }

    fun hasMatchedEdge(other: Tile) : Boolean = edges.count { edge -> other.hasMatch(edge) } > 0

    fun hasMatch(edge: String): Boolean = edge in edges

    override fun toString(): String {
        return pixels
            .toList()
            .chunked(width)
            .joinToString("\n") {
                it.joinToString("")
            }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tile

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}

enum class Orientation {
    Top, Right, Bottom, Left
}

class Day20(val input: String) {

    private val tiles: List<Tile> = parse()

    fun parse(): List<Tile> {
        return input
            .split("\n\n")
            .map { each ->
                val (identity, pixels) = each.split(":")
                val id = identity.substringAfter(" ").substringBefore(":").toInt()
                Tile(id, pixels.trim())
            }
    }

    fun countTotalEdges() : Int = tiles.sumOf { tile -> tile.edges.size }

    private fun buildSharedEdges(): Map<Int,Int> {
        val edgeMap: MutableMap<Int,Int> = mutableMapOf()
        tiles.map { tile ->
            edgeMap[tile.id] = tiles
                .filterNot { tile.id == it.id }
                .count { other -> tile.hasMatchedEdge(other) }
        }
        return edgeMap
    }

//    corner == 2 sides in common
//    edge == 3 sides in common
//    inner = 4 sides in common
    fun partOne(): Long {
        return buildSharedEdges()
            .filterValues { it == 2 }
            .keys
            .fold(1L, { acc, item -> item * acc })
    }

}

fun main(args: Array<String>) {
    val input = fileAsString("day20_2020.txt")
    val solver = Day20(input)
    println(solver.partOne())
}
