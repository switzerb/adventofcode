package aoc.lib



//fun GetInput(clazz: Class<T>, resource: String): String {
//    val cl = clazz::class.java.classLoader.getResource(resource) ?: return ""
//    return cl.readText()
//}

inline fun <reified T> getInput(resource: String): String {
    val cl = T::class.java.classLoader.getResource(resource) ?: null
    return cl?.readText() ?: ""

}
