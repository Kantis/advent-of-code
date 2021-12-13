sealed interface Cave {
    val name: String
}

data class BigCave(override val name: String) : Cave
data class SmallCave(override val name: String) : Cave

data class Edge(val a: Cave, val b: Cave)

fun Edge.touches(n: Cave) = a == n || b == n
fun Edge.other(n: Cave) = if (a == n) b else a

fun cave(name: String): Cave =
    if (name.any { it.isLowerCase() }) SmallCave(name)
    else BigCave(name)

data class Graph(
    val nodes: Set<Cave>,
    val edges: Set<Edge>,
) {
    fun start() = nodes.single { it.name == "start" }
    fun end() = nodes.single { it.name == "end" }
}

@JvmInline
value class Path(val edges: List<Edge> = emptyList()) {
    override fun toString(): String =
        edges.joinToString(
            separator = " | "
        ) { (a, b) -> "${a.name}->${b.name}" }
}

operator fun Edge.plus(path: Path) = Path(path.edges + this)

operator fun Edge.times(paths: Set<Path>) =
    paths.map { path -> this + path }.toSet()

@Suppress("MemberVisibilityCanBePrivate")
object Day12 {

    fun Graph.paths(
        start: Cave,
        end: Cave,
        visitedSmallCaves: Set<Cave> = emptySet(),
        anySmallCaveVisitedTwice: Boolean = false,
        firstIteration: Boolean = true
    ): Set<Path> {
        if (start == end) return setOf(Path())

        // TODO: Prevent visiting end/start twice
        return edges
            .filter { it.touches(end) }
            .filter { if (anySmallCaveVisitedTwice) it.other(end) !in visitedSmallCaves else true }
            .filter { if (!firstIteration) !it.touches(end()) else true }
            .flatMap { edge ->
                edge * paths(
                    start,
                    edge.other(end),
                    visitedSmallCaves + if (end is SmallCave) setOf(end) else emptySet(),
                    if (anySmallCaveVisitedTwice) true else edge.other(end) in visitedSmallCaves,
                    false
                )
            }.toSet()
    }

    fun parse(input: String): Graph {
        val nodes = mutableSetOf<Cave>()
        val edges = mutableSetOf<Edge>()

        input.lines().map { it.split("-") }
            .forEach { (start, end) ->
                nodes.add(cave(start))
                nodes.add(cave(end))
                edges.add(Edge(cave(start), cave(end)))
            }

        return Graph(nodes, edges)
    }

    fun part1(input: String): Int {
        val graph = parse(input)
        return graph.paths(
            graph.start(),
            graph.end(),
            anySmallCaveVisitedTwice = true
        ).size
    }

    fun part2(input: String): Int {
        val graph = parse(input)
        return graph.paths(
            graph.start(),
            graph.end(),
            anySmallCaveVisitedTwice = false
        ).onEach { println(it) }.size
    }
}
