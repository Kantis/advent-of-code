package aoc2021

import memoize
import kotlin.jvm.JvmInline

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
    val end = memoize<Cave> { nodes.single { it.name == "end" } }
    val lookup = nodes.associateWith { cave -> edges.filter { it.touches(cave) } }
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
        target: Cave,
        visitedSmallCaves: Set<Cave> = emptySet(),
        anySmallCaveVisitedTwice: Boolean = false,
        firstIteration: Boolean = true
    ): Set<Path> {
        if (start == target) return setOf(Path())

        return lookup[target]!!
            .filter { if (anySmallCaveVisitedTwice) it.other(target) !in visitedSmallCaves else true }
            .filter { if (!firstIteration) !it.touches(end()) else true }
            .flatMap { edge ->
                edge * paths(
                    start,
                    edge.other(target),
                    visitedSmallCaves + if (target is SmallCave) setOf(target) else emptySet(),
                    if (anySmallCaveVisitedTwice) true else edge.other(target) in visitedSmallCaves,
                    false
                )
            }.toSet()
    }

    fun parse(input: String): Graph {
        val nodes = mutableSetOf<Cave>()
        val edges = mutableSetOf<Edge>()

        input.lines()
            .filter { it.isNotBlank() }
            .map { it.split("-") }
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
        ).size
    }
}

fun readAllInput() =
    buildString {
        do {
            val x = readlnOrNull()
            if (x != null) appendLine(x)
        } while (x != null)
    }

fun main(args: Array<String>) {
    val part = args[0].toInt()
    val input = readAllInput()

    when (part) {
        1 -> println(Day12.part1(input))
        2 -> println(Day12.part2(input))
        else -> error("first argument should be part nr. [1, 2]")
    }
}