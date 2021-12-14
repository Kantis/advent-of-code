typealias Heightmap = Array<IntArray>

object Day9 {
    data class Point(val x: Int, val y: Int)

    private fun Point.neighbours(map: Heightmap) = buildSet {
        if (x > 0) add(Point(x - 1, y))
        if (x < map.size - 1) add(Point(x + 1, y))
        if (y > 0) add(Point(x, y - 1))
        if (y < map[0].size - 1) add(Point(x, y + 1))
    }

    private operator fun Heightmap.get(point: Point) = this[point.x][point.y]
    private fun Heightmap.isLowPoint(point: Point) =
        this[point] < point.neighbours(this).minOf { this[it] }

    private fun lowpoints(map: Heightmap) = sequence {
        for (x in map.indices) {
            for (y in map[0].indices) {
                if (map.isLowPoint(Point(x, y))) yield(Point(x, y))
            }
        }
    }

    private fun parseMap(lines: List<String>): Heightmap =
        lines.map { it.map { it.digitToInt() }.toIntArray() }.toTypedArray()

    private fun basinSize(map: Heightmap, visited: MutableSet<Point>, point: Point): Int {
        return when {
            map[point.x][point.y] == 9 -> 0
            visited.contains(point) -> 0
            else -> {
                visited.add(point)
                1 + point.neighbours(map).sumOf { basinSize(map, visited, it) }
            }
        }
    }

    fun part1(lines: List<String>): Int {
        val map = parseMap(lines)
        return lowpoints(map).sumOf { map[it] + 1 }
    }

    fun part2(lines: List<String>): Int {
        val map = parseMap(lines)
        val visited = mutableSetOf<Point>()
        return lowpoints(map).map { basinSize(map, visited, it) }.sortedDescending().take(3).reduce(Int::times)
    }
}
