package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DaySixteenTest {

    private val literalHex = "D2FE28"
    private val opHex1 = "38006F45291200"
    private val opHex2 = "8A004A801A8002F478"

    @Test
    fun testToBinary() {
        assertEquals("110100101111111000101000", DaySixteen.toBinary(literalHex))
    }

    @Test
    fun testProcessPacketLiteralOnly() {
        val solver = DaySixteen(literalHex)
        val (packet, bitCount) = solver.processPacket()
        assertEquals(6, packet.version)
        assertEquals(4, packet.type)
        assertEquals(2021, packet.value)
        assertEquals(21, bitCount)
    }

    @Test
    fun testProcessPacketOperator() {
        val solver = DaySixteen(opHex1)
        val (root, bitCount) = solver.processPacket()
        assertEquals(1, root.version)
        assertEquals(6, root.type)
        assertEquals(2, root.subPackets.size)
        assertEquals(10, root.subPackets[0].value)
        assertEquals(20, root.subPackets[1].value)
    }

    @Test
    fun test() {
        val solver = DaySixteen(literalHex)

        /* OPERATOR
        * 00111000000000000110111101000101001010010001001000000000
        * VVVTTTILLLLLLLLLLLLLLLAAAAAAAAAAABBBBBBBBBBBBBBBB
        *
        * length type ID 0 - next 15 bits are a number that represents the total length in bits of the sub-packets contained by this packet.
        * length type ID 1 - next 11 bits are a number that represents the number of sub-packets immediately contained by this packet.
        *
        * The 15 bits labeled L (000000000011011) contain the length of the sub-packets in bits, 27.
        *
        * packet one
        * 110 100 0 1010 (literal value 10)
        *
        * packet two
        * 010 100 1 0001 0 0100 = 00010100 (literal value 20)
        */
        assertEquals(16, solver.partOne())
    }
}
