object Day14 {
    fun parseInput(lines: String): Pair<String, Map<String, String>> {
        return lines.split("\n\n").let { (template, transforms) ->
            template to transforms.split("\n").filter { it.isNotBlank() }.associate { line ->
                line.split(" -> ").let { (sequence, result) ->
                    sequence to result
                }
            }
        }
    }

    fun part1(lines: String, iterations: Int = 10): Int {
        val (template, transforms) = parseInput(lines)
        return (1..iterations).fold(template) { acc, _ ->
            acc.windowed(2).joinToString(prefix = acc[0].toString(), separator = "") { transforms[it]!! + it[1] }
        }.let {
            val byCount = it.groupBy { it }.mapValues { (_, v) -> v.count() }
            println(byCount)
            byCount.maxOf { it.value } - byCount.minOf { it.value }
        }
    }

    fun part2(lines: String): Long {
        data class Generator(
            val name: String,
            val outputChar: Char,
        )

        data class DirectedEdge(
            val origin: Generator,
            val output: Pair<Generator, Generator>
        )

        data class State(
            val generators: List<Generator>,
            val edges: List<DirectedEdge>,
            val generatorCounts: Map<Generator, Long>,
            val charCounts: Map<Char, Long>,
        )

        val (template, transforms) = parseInput(lines)

        // build transform relations:
        val tl = transforms.toList()

        val generators = tl.map { (sequence, generated) ->
            Generator(sequence, generated.single())
        }

        val edges: List<DirectedEdge> =
            generators.map { origin ->
                DirectedEdge(
                    origin,
                    Pair(
                        generators.single { it.name == origin.name[0] + "${origin.outputChar}" },
                        generators.single { it.name == "${origin.outputChar}" + origin.name[1] }
                    )
                )
            }

        println(edges)

        return (1..40).fold(
            State(
                generators,
                edges,
                generators.associateWith { gen -> template.windowed(2).count { it == gen.name }.toLong() },
                template.groupBy { it }.mapValues { (_, v) -> v.count().toLong() }
            )
        ) { acc, _ ->
            val nextGeneratorCounts = acc.generatorCounts.toMutableMap()
            val nextCharCounts = acc.charCounts.toMutableMap()

            acc.generators.forEach { gen ->
                nextGeneratorCounts[gen] = nextGeneratorCounts[gen]!! - acc.generatorCounts[gen]!!

                val targets = edges.single { it.origin == gen }.output
                nextGeneratorCounts[targets.first] = nextGeneratorCounts[targets.first]!! + acc.generatorCounts[gen]!!
                nextGeneratorCounts[targets.second] = nextGeneratorCounts[targets.second]!! + acc.generatorCounts[gen]!!

                nextCharCounts[gen.outputChar] = (nextCharCounts[gen.outputChar] ?: 0) + acc.generatorCounts[gen]!!
            }

            State(
                acc.generators,
                acc.edges,
                nextGeneratorCounts,
                nextCharCounts
            )
        }.let {
            it.charCounts.maxOf { it.value } - it.charCounts.minOf { it.value }
        }
    }
}
