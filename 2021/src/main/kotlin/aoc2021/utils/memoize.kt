
inline fun <reified S, reified T> memoize(crossinline fn: (S) -> T): (S) -> T {
    val results = mutableMapOf<S, T>()
    return { s ->
        if (results[s] == null) {
            results[s] = fn(s)
        }
        results[s]!!
    }
}

inline fun <reified T> memoize(crossinline fn: () -> T): () -> T {
    var result: T? = null
    return {
        if (result != null) {
            result!!
        } else {
            result = fn()
            result!!
        }
    }
}
