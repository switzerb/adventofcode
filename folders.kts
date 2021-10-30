import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter

val BASE = "app/src"

// base source directories
val base_src = "${BASE}/main/kotlin/aoc/"
val base_src_resources = "${BASE}/main/resources/"

val base_test = "${BASE}/test/kotlin/aoc/"
val base_test_resources = "${BASE}/test/resources/"

// Get day and year args from stdin
val day =
    if (args.contains("-d")) args[1 + args.indexOf("-d")]
    else "00"
val year =
    if (args.contains("-y")) args[1 + args.indexOf("-y")]
    else "2021"

// Set up source
val dayAsName: String = "Day${dayTranslated(day)}"
val src_file = File("${base_src}/y${year}/${dayAsName}.kt")
val test_file = File("${base_test}/y${year}/${dayAsName}Test.kt")
val input_src_file = File("${base_src_resources}/day${day}_${year}.txt")
val input_test_file = File("${base_test_resources}/day${day}_${year}.txt")

val mainText = """
    package aoc.y$year
    import aoc.lib.Resources.fileAsString

    class $dayAsName(private val input: String) {
    
        fun partOne(): String {
           return input
        }

        fun partTwo() {}    
    }
    
    fun main(args: Array<String>) {
        val input = fileAsString("day${day}_${year}.txt")
        val solver = $dayAsName(input)
        println(solver.partOne())
    }

""".trimIndent()

val testText = """
    package aoc.y$year

    import kotlin.test.Test
    import kotlin.test.assertTrue

    class ${dayAsName}Test {
    
        @Test fun test() {
            assertTrue(true)
        }
    }

""".trimIndent()

fun bufferedWriter(file: File, text: String) {
    if(!file.exists()) {
        file.createNewFile()
    }
    val writer = BufferedWriter(FileWriter(file))
    writer.write(text)
    writer.close()
}

fun dayTranslated(day: String): String {
    return when(day) {
        "01", "1"-> "One"
        "02", "2"-> "Two"
        "03", "3"-> "Three"
        "04", "4"-> "Four"
        "05", "5"-> "Five"
        "06", "6"-> "Six"
        "07", "7"-> "Seven"
        "08", "8"-> "Eight"
        "09", "9"-> "Nine"
        "10" -> "Ten"
        "11" -> "Eleven"
        "12" -> "Twelve"
        "13" -> "Thirteen"
        "14" -> "Fourteen"
        "15" -> "Fifteen"
        "16" -> "Sixteen"
        "17" -> "Seventeen"
        "18" -> "Eighteen"
        "19" -> "Nineteen"
        "20" -> "Twenty"
        "21" -> "TwentyOne"
        "22" -> "TwentyTwo"
        "23" -> "TwentyThree"
        "24" -> "TwentyFour"
        "25" -> "TwentyFive"
        else -> "Zero"
    }
}

// day src file
bufferedWriter(
    file = src_file,
    text = mainText
)
bufferedWriter(
    file = input_src_file,
    text = "Hello, World!"
)
bufferedWriter(
    file = test_file,
    text = testText
)
bufferedWriter(
    file = input_test_file,
    text = "Hello, World!"
)
