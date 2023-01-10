package aoc.y2022

import aoc.lib.Resources.fileAsString
import org.jgrapht.Graphs.neighborSetOf
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph
import org.jgrapht.nio.Attribute
import org.jgrapht.nio.DefaultAttribute
import org.jgrapht.nio.dot.DOTExporter
import java.io.StringWriter
import java.io.Writer
import kotlin.collections.LinkedHashMap

const val TIME_ALLOWED = 30
const val START = "AA"

data class Valve(val name: String, val flow: Int, val tunnels: List<String>) {
    override fun toString(): String {
        return "$name ($flow)"
    }
}

data class Step(
    val valve: Valve,
    val timeRemaining: Int = 0,
    val totalFlow: Int = 0,
    val openValves: List<Valve> = listOf()
) {
    //    (20 * 3) + (20 + 13 * 4) + (54 * 8) + (76 * 4) + (79 * 3) + (81 * 6) = 1651
    fun moveToAndOpen(valve: Valve, graph: ValveGraph): Step {
        val edge = graph.getEdge(this.valve, valve)
            ?: throw IllegalStateException("Can't find edge for ${this.valve} and $valve")
        val timeElapsed = this.timeRemaining + graph.getEdgeWeight(edge).toInt()
        return Step(
            valve = valve,
            timeRemaining = (TIME_ALLOWED - timeElapsed),
            totalFlow = totalFlow + ((TIME_ALLOWED - timeElapsed) * valve.flow),
            openValves = this.openValves + valve
        )
    }
}

private typealias ValveGraph = SimpleWeightedGraph<Valve, WeightedEdge>

fun ValveGraph.fromName(name: String) =
    this.vertexSet().stream().filter { vertex -> vertex.name == name }.findAny().get()

class WeightedEdge : DefaultWeightedEdge() {
    public override fun getWeight(): Double {
        return super.getWeight()
    }
}

class DaySixteen(private val input: String) {

    val valves: List<Valve> = buildList {
        input.split("\n")
            .map { line ->
                val (info, connectors) = line.split(";")
                val name = info.split(" ")
                val flowRate = info.substringAfter("=").toInt()
                val (_, tunnels) = connectors.split("valves|valve".toRegex())
                add(Valve(name = name[1], flow = flowRate, tunnels = tunnels.trim().split(", ")))
            }
    }

    private fun parse(): ValveGraph {
        val graph = ValveGraph(WeightedEdge::class.java)
        valves.forEach { graph.addVertex(it) }
        valves.forEach { vertex ->
            for (tunnel in vertex.tunnels) {
                val other = graph.fromName(tunnel)
                graph.addEdge(vertex, other)
            }
        }
//        println(printGraphviz(graph))
        return graph
    }

    private fun printGraphviz(graph: ValveGraph): String {
        val exporter: DOTExporter<Valve, WeightedEdge> = DOTExporter { v -> v.name }
        exporter.setVertexAttributeProvider { v ->
            val map: MutableMap<String, Attribute> = LinkedHashMap()
            map["label"] = DefaultAttribute.createAttribute(v.toString())
            map
        }
        exporter.setEdgeAttributeProvider { e ->
            val map: MutableMap<String, Attribute> = LinkedHashMap()
            map["label"] = DefaultAttribute.createAttribute(e.weight.toString())
            map
        }
        val writer: Writer = StringWriter()
        exporter.exportGraph(graph, writer)
        return writer.toString()
    }

    fun search(valveGraph: ValveGraph, start: Valve): Int {
        val visited = mutableSetOf<Step>()
        val queue = mutableListOf<Step>()
        val max: Int = Int.MIN_VALUE
        val step = Step(
            valve = start,
            timeRemaining = TIME_ALLOWED,
            totalFlow = 0,
            openValves = listOf(start)
        )
        queue.add(step)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            // if minutes are greater than 30 we need to stop or if all the valves are open
            if (current.openValves.size == valveGraph.vertexSet().size) {
                println("stop")
            }

            if (current !in visited) {
                visited.add(current)
                val neighbors: Set<Valve> =
                    neighborSetOf(valveGraph, current.valve).filter { valve -> valve !in current.openValves }.toSet()

                for (neighbor in neighbors) {
                    val next = current.moveToAndOpen(
                        valve = neighbor,
                        graph = valveGraph
                    )
                    queue.add(next)
                }
            }
        }
        return 0
    }

    fun buildValveGraph(): ValveGraph {
        val graph = parse()
        val startValve = graph.fromName(START)
        val valveGraph = ValveGraph(WeightedEdge::class.java)
        val shortestPaths = FloydWarshallShortestPaths(graph)
        valveGraph.addVertex(startValve)
        val withFlow = valves.filter { valve -> valve.flow > 0 || valve.name == "AA" }
        withFlow.forEach { vertex ->
            valveGraph.addVertex(vertex)
        }
        for (i in 0..withFlow.size - 1) {
            for (j in i..withFlow.size - 1) {
                val v1 = withFlow[i]
                val v2 = withFlow[j]
                if (v1 != v2 && valveGraph.getEdge(v1, v2) == null) {
                    val edge = valveGraph.addEdge(v1, v2)
                    valveGraph.setEdgeWeight(edge, shortestPaths.getPathWeight(v1, v2))
                }
            }
        }
        return valveGraph
    }

    fun partOne(): Int {
        val graph = buildValveGraph()
        val start = graph.fromName(START)

//        println(printGraphviz(valveGraph))
        search(graph, start)
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day16_2022.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
}
