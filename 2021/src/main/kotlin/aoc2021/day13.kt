package aoc2021

object day13 {
    private data class Point(val x: Int, val y: Int)

    private fun Set<Point>.foldX(f: Int) =
        map { (x, y) -> Point(if (x >= f) 2 * f - x else x, y) }.toSet()

    private fun Set<Point>.foldY(f: Int) =
        map { (x, y) -> Point(x, if (y >= f) 2 * f - y else y) }.toSet()

    private fun interface PaperFold {
        operator fun invoke(points: Set<Point>): Set<Point>
    }

    private fun compose(f1: PaperFold, f2: PaperFold) =
        PaperFold { points -> f2(f1(points)) }

    private fun parseInput(lines: String): Pair<Set<Point>, List<PaperFold>> {
        val (pointInfo, foldInstructions) = lines.split("\n\n")
        val points = pointInfo.lines().map {
            it.split(",").let { (x, y) ->
                Point(x.toInt(), y.toInt())
            }
        }.toSet()

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

        return folds[0](points).count()
    }

    fun part2(lines: String) {
        val (points, folds) = parseInput(lines)
        val f = folds.reduce(day13::compose)
        val folded = f(points)

        for (y in 0..100) {
            for (x in 0..100) {
                if (folded.contains(Point(x, y))) print('#') else print('.')
            }
            println()
        }
    }
}

fun main() {}
