object Day11 {

    data class Point(val x: Int, val y: Int)

    data class Octopus(
        val location: Point,
        var counter: Int,
        var fired: Boolean = false,
        var fireCount: Int = 0,
    )

    private fun Point.neighbours(xRange: IntRange, yRange: IntRange) = buildSet {
        add(Point(x - 1, y))
        add(Point(x - 1, y - 1))
        add(Point(x - 1, y + 1))

        add(Point(x, y - 1))
        add(Point(x, y + 1))

        add(Point(x + 1, y))
        add(Point(x + 1, y - 1))
        add(Point(x + 1, y + 1))
    }.filter { it.x in xRange && it.y in yRange }

    fun Collection<Octopus>.iterate(
        xRange: IntRange = 0..maxOf { it.location.x },
        yRange: IntRange = 0..maxOf { it.location.y }
    ): Collection<Octopus> {
        onEach { it.fired = false }
        val byPoint = associateBy { it.location }

        fun increment(point: Point) {
            with(byPoint[point]!!) {
                if (!fired) {
                    counter++
                    if (counter > 9) {
                        counter = 0
                        fired = true
                        fireCount++

                        point.neighbours(xRange, yRange).onEach { increment(it) }
                    }
                }
            }
        }

        byPoint.keys.onEach { increment(it) }
        return byPoint.values
    }

    fun part1(lines: String, iterations: Int): Int {
        val octopi: Collection<Octopus> = lines.split("\n")
            .mapIndexed { x, line ->
                line.mapIndexed { y, char ->
                    Octopus(
                        Point(x, y),
                        char.digitToInt(),
                    )
                }
            }.flatten()

        repeat(iterations) { octopi.iterate() }
        return octopi.sumOf { it.fireCount }
    }

    fun part2(lines: String): Int {
        val octopi: Collection<Octopus> = lines.split("\n")
            .mapIndexed { x, line ->
                line.mapIndexed { y, char ->
                    Octopus(
                        Point(x, y),
                        char.digitToInt(),
                    )
                }
            }.flatten()

        var i = 0
        do {
            i++
            octopi.iterate()
        } while (!octopi.all { it.fired })
        return i
    }
}
