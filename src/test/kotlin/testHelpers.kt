inline fun <reified T : Any> T.loadAsText(path: String) =
    T::class.java.getResource(path)?.readText() ?: error("Failed to load $path")
