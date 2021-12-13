inline fun <reified S, reified T> memoize(crossinline fn: (S) -> T): (S) -> T {
    val results = mutableMapOf<S, T>()
    return { s ->
        results.computeIfAbsent(s) { fn(s) }
        results[s]!!
    }
}
