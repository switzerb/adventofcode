package aoc.y2022

import aoc.lib.Resources.fileAsString
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge

data class Valve(val name: String, val flow: Int)

class DaySixteen(private val input: String) {

    val AA = Valve(name = "AA", flow = 0)
    val BB = Valve(name = "AA", flow = 0)
    val CC = Valve(name = "CC", flow = 2)
    val DD = Valve(name = "DD", flow = 20)
    val EE = Valve(name = "EE", flow = 3)
    val FF = Valve(name = "FF", flow = 0)
    val GG = Valve(name = "GG", flow = 0)
    val HH = Valve(name = "HH", flow = 2)
    val II = Valve(name = "II", flow = 0)
    val JJ = Valve(name = "JJ", flow = 2)

    fun partOne(): Int {
        val graph: Graph<Valve, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)
        graph.addVertex(AA)
        graph.addVertex(BB)
        graph.addVertex(CC)
        graph.addVertex(DD)
        graph.addVertex(EE)
        graph.addVertex(FF)
        graph.addVertex(GG)
        graph.addVertex(HH)
        graph.addVertex(II)
        graph.addVertex(JJ)

        graph.addEdge(AA, DD)
        graph.addEdge(AA, II)
        graph.addEdge(AA, BB)
        graph.addEdge(BB, CC)
        graph.addEdge(BB, AA)

        println(graph)
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day16_2022.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
}
