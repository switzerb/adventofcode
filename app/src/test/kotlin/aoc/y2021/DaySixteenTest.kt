package aoc.y2021

import org.junit.Assert.assertTrue
import org.junit.Test

class DaySixteenTest {

    val exInput = "D2FE28"

    @Test
    fun test() {
        val solver = DaySixteen(exInput)
//        110100101111111000101000
//        VVVTTTAAAAABBBBBCCCCC

        // LITERAL
        // convert to binary should be 110100101111111000101000
        // version 110
        // type 100 (4), LITERAL VALUE
        // first group - prefix + 4 bits 1-0111 (prefix one means not done)
        // second group - prefix + 4 bits 1-1110 (prefix one means not done)
        // third ground = prefix + 4 bits 0-0101 (prefix means last group)
        // literal value = 0111 1110 0101
        // convert to decimal = 2021

        /* OPERATOR
        * 00111000000000000110111101000101001010010001001000000000
        * VVVTTTILLLLLLLLLLLLLLLAAAAAAAAAAABBBBBBBBBBBBBBBB
        *
        * version 001
        * type 110 (6)
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
        assertTrue(true)
    }
}
