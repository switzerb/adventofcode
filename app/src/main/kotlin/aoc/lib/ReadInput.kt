package aoc.lib

import java.io.File
import java.net.URI

inline fun <reified T> getInput(resource: String): String {
    val cl = T::class.java.classLoader.getResource(resource) ?: null
    return cl?.readText() ?: ""
}

object Resources {

    private fun rawAsList(input: String): List<String> = input.split("\n")

    fun rawAsInt(input: String): List<Int> = rawAsList(input).map { it.toInt() }

    fun rawAsLong(input: String): List<Long> = rawAsList(input).map { it.toLong() }

    private fun fileAsList(fileName: String): List<String> =
            File(fileName.toURI()).readLines()

    fun fileAsLong(fileName: String): List<Long> =
            fileAsList(fileName).map { it.toLong() }

    fun fileAsInt(fileName: String): List<Int> =
        fileAsList(fileName).map { it.toInt() }

    private fun String.toURI(): URI =
            Resources::class.java.classLoader.getResource(this)?.toURI() ?: throw IllegalArgumentException("No resource found: $this")
}
