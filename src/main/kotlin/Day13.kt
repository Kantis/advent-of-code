object Day13 {
    data class Point(val x: Int, val y: Int)

    private fun List<Point>.foldX(f: Int) =
        map { (x, y) -> Point(if (x >= f) 2 * f - x else x, y) }

    private fun List<Point>.foldY(f: Int) =
        map { (x, y) -> Point(x, if (y >= f) 2 * f - y else y) }

    fun interface PaperFold {
        fun fold(points: List<Point>): List<Point>
    }

    private fun parseInput(lines: String): Pair<List<Point>, List<PaperFold>> {
        val (pointInfo, foldInstructions) = lines.split("\n\n")
        val points = pointInfo.lines().map { it.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) } }

        val folds = foldInstructions.lines().map {
            it.split("=").let { (axis, pos) ->
                if (axis.endsWith("x")) PaperFold { points -> points.foldX(pos.toInt()) }
                else PaperFold { points -> points.foldY(pos.toInt()) }
            }
        }
        return Pair(points, folds)
    }

    fun part1(lines: String): Int {
        val (points, folds) = parseInput(lines)

        return folds[0].fold(points).distinct().onEach { println(it) }.count()
    }

    fun part2(lines: String) {
        val (points, folds) = parseInput(lines)

        var p = points
        for (f in folds) {
            p = f.fold(p)
        }

        for (y in 0..100) {
            for (x in 0..100) {
                if (p.contains(Point(x, y))) print('#') else print('.')
            }
            println()
        }
    }
}
