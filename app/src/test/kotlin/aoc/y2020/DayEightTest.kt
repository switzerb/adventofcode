import org.junit.Test
import kotlin.test.assertTrue

class DayEight2020Test {

//    val input = """
//        nop +0
//        acc +1
//        jmp +4
//        acc +3
//        jmp -3
//        acc -99
//        acc +1
//        jmp -4
//        acc +6
//    """.trimIndent()

    @Test
    fun ex1() {


        if(visited.contains(1)) {
            isLoop = true
        }
        assertTrue(isLoop)
    }
}
