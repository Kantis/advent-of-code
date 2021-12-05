// Vent spec: 0,9 -> 5,9

object Day5 {
    data class Point(val x: Int, val y: Int)

    fun smartRange(p1: Int, p2: Int): IntProgression = if (p1 < p2) p1..p2 else p1 downTo p2
    data class Line(val p1: Point, val p2: Point) {
        fun isHorizontal() = p1.y == p2.y
        fun isVertical() = p1.x == p2.x
        fun isStraight() = isHorizontal() || isVertical()

        fun allPoints(): List<Point> =
            when {
                isHorizontal() -> smartRange(p1.x, p2.x).map { Point(it, p1.y) }
                isVertical() -> smartRange(p1.y, p2.y).map { Point(p1.x, it) }
                else -> smartRange(p1.x, p2.x).zip(smartRange(p1.y, p2.y)).map { (x, y) -> Point(x, y) }
            }
    }

    fun part1(input: Sequence<String>): Int {
        val lines = input.map {
            it.split("->").map { p -> p.trim().split(",").let { Point(it[0].toInt(), it[1].toInt()) } }.let {
                Line(it[0], it[1])
            }
        }

        return lines
            .filter { it.isStraight() }
            .flatMap { it.allPoints() }
            .groupBy { it }
            .filter { it.value.count() > 1 }
            .count()
    }

    fun part2(input: Sequence<String>): Int {
        val lines = input.map {
            it.split("->").map { p -> p.trim().split(",").let { Point(it[0].toInt(), it[1].toInt()) } }.let {
                Line(it[0], it[1])
            }
        }

        return lines
            .flatMap { it.allPoints() }
            .groupBy { it }
            .filter { it.value.count() > 1 }
            .count()
    }
}

fun main() {
    Day5.part1(generateSequence(::readLine))
}
