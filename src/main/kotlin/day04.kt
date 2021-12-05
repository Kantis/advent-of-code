typealias Board = Array<Array<Slot>>

data class Slot(
    val number: Int,
    var marked: Boolean
)

val whitespace = """\s+""".toRegex()

object Day4 {
    fun toBoard(board: String): Board =
        board.split("\n")
            .map { line ->
                val split = line.trim().split(whitespace)
                split.map {
                    Slot(it.toInt(), false)
                }.toTypedArray()
            }
            .toTypedArray()

    fun Board.isWinner() =
        this.indices.any { i ->
            this[i].all { (_, marked) -> marked } ||
                all { rows -> rows[i].marked }
        }

    fun Board.mark(number: Int) {
        for (row in this) {
            for (col in row) {
                if (col.number == number) {
                    col.marked = true
                }
            }
        }
    }

    fun part1(lines: String): Int {
        val split = lines.split("\n\n").toList()
        val drawOrder = split[0].split(",").map { it.toInt() }
        val boards = split.drop(1)
            .map(::toBoard)

        for (i in drawOrder) {
            boards.forEach { it.mark(i) }
            val winner = boards.singleOrNull { it.isWinner() }
            if (winner != null) {
                println("Winner at $i")
                val unmarkedNumbers = winner.sumOf { row -> row.sumOf { if (it.marked) 0 else it.number } }
                return unmarkedNumbers * i
            }
        }

        error("No winner after drawing all numbers")
    }

    fun part2(lines: String): Int {
        val split = lines.split("\n\n").toList()
        val drawOrder = split[0].split(",").map { it.toInt() }
        val boards = split.drop(1)
            .map(::toBoard)
            .toMutableList()

        for (i in drawOrder) {
            boards.forEach { it.mark(i) }
            if (boards.count() > 1) {
                boards.removeAll { it.isWinner() }
            } else if (boards[0].isWinner()) {
                println("Winner at $i")
                val unmarkedNumbers = boards[0].sumOf { row -> row.sumOf { if (it.marked) 0 else it.number } }
                return unmarkedNumbers * i
            }
        }

        error("No winner after drawing all numbers")
    }
}

fun main() {
}
