package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DaySixteenTest {

    val input = """
        Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
        Valve BB has flow rate=13; tunnels lead to valves CC, AA
        Valve CC has flow rate=2; tunnels lead to valves DD, BB
        Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
        Valve EE has flow rate=3; tunnels lead to valves FF, DD
        Valve FF has flow rate=0; tunnels lead to valves EE, GG
        Valve GG has flow rate=0; tunnels lead to valves FF, HH
        Valve HH has flow rate=22; tunnel leads to valve GG
        Valve II has flow rate=0; tunnels lead to valves AA, JJ
        Valve JJ has flow rate=21; tunnel leads to valve II
    """.trimIndent()

    @Test
    fun test() {
        val solver = DaySixteen(input)
        assertEquals(1651, solver.partOne())
    }

    @Test
    fun t1_p1() {
        val solver = DaySixteen(input)
        val graph = solver.buildValveGraph()
        val start = graph.fromName("AA")
        val end = graph.fromName("DD")
        val step = Step(
            valve = graph.fromName("AA"),
            timeRemaining = 1,
            totalFlow = 0,
            openValves = listOf(start)
        )
        assertEquals(
            step.moveToAndOpen(
                valve = end,
                graph = graph
            ),
            Step(
                valve = end,
                timeRemaining = 3,
                totalFlow = 560,
                openValves = listOf(start, end)
            )
        )
    }

    @Test
    fun t2_p1() {
        val solver = DaySixteen(input)
        val graph = solver.buildValveGraph()
        val AA = graph.fromName("AA")
        val DD = graph.fromName("DD")
        val BB = graph.fromName("BB")
        val JJ = graph.fromName("JJ")
        val HH = graph.fromName("HH")
        val EE = graph.fromName("EE")
        val CC = graph.fromName("CC")
        val step = Step(
            valve = EE,
            timeRemaining = 9,
            totalFlow = 0,
            openValves = listOf(AA, DD, BB, JJ, HH, EE)
        )
        assertEquals(
            Step(
                valve = CC,
                timeRemaining = 6,
                totalFlow = 1651,
                openValves = listOf(AA, DD, BB, JJ, HH, EE, CC)
            ),
            step.moveToAndOpen(
                valve = CC,
                graph = graph
            )
        )
    }
}
