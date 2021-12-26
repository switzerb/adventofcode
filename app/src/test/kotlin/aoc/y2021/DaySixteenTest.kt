package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DaySixteenTest {

    private val literalHex = "D2FE28"
    private val opHex1 = "38006F45291200"
    private val opHex2 = "EE00D40C823060"

    @Test
    fun testToBinary() {
        assertEquals("110100101111111000101000", DaySixteen.toBinary(literalHex))
    }

    @Test
    fun testProcessPacketLiteralOnly() {
        val solver = DaySixteen(literalHex)
        val packet = solver.processPacket()
        assertEquals(6, packet.version)
        assertEquals(4, packet.type)
        assertEquals(2021, packet.value)
        assertEquals(21, packet.totalBits)
    }

    @Test
    fun testProcessPacketOperator1() {
        val solver = DaySixteen(opHex1)
        val root = solver.processPacket()
        assertEquals(1, root.version)
        assertEquals(6, root.type)
        assertEquals(2, root.subPackets.size)
        assertEquals(10, root.subPackets[0].value)
        assertEquals(11, root.subPackets[0].totalBits)
        assertEquals(20, root.subPackets[1].value)
        assertEquals(16, root.subPackets[1].totalBits)
    }

    @Test
    fun testProcessPacketOperator2() {
        val solver = DaySixteen(opHex2)
        val root = solver.processPacket()
        assertEquals(7, root.version)
        assertEquals(3, root.type)
        assertEquals(3, root.subPackets.size)
        assertEquals(1, root.subPackets[0].value)
        assertEquals(2, root.subPackets[1].value)
        assertEquals(3, root.subPackets[2].value)
    }

    @Test
    fun test() {
        val solver = DaySixteen(literalHex)
        assertEquals(16, solver.partOne())
    }
}
