package aoc.lib

import java.io.File
import java.net.URI

inline fun <reified T> getInput(resource: String): String {
    val cl = T::class.java.classLoader.getResource(resource) ?: null
    return cl?.readText() ?: ""
}

object Resources {

    // TODO: pass in the function that you want to be applied to the list

    fun rawAsList(input: String): List<String> = input.trim().split("\n")

    fun rawAsInts(input: String): List<Int> = rawAsList(input).map { it.toInt() }

    fun rawAsLongs(input: String): List<Long> = rawAsList(input).map { it.toLong() }

    fun fileAsList(fileName: String): List<String> =
            File(fileName.toURI()).readLines()

    fun fileAsLongs(fileName: String): List<Long> =
            fileAsList(fileName).map { it.toLong() }

    fun fileAsInts(fileName: String): List<Int> =
        fileAsList(fileName).map { it.toInt() }

    fun fileAsString(fileName: String): String = File(fileName.toURI()).readText().trim()

    private fun String.toURI(): URI =
            Resources::class.java.classLoader.getResource(this)?.toURI() ?: throw IllegalArgumentException("No resource found: $this")
}
