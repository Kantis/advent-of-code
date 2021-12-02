object Day2 {
    data class Movement(
        val horizontal: Int = 0,
        val depth: Int = 0
    ) {
        operator fun plus(other: Movement) =
            Movement(horizontal + other.horizontal, depth + other.depth)
    }

    fun part1(lines: Sequence<String>): Int {
        val position = lines
            .filterNot { it.isBlank() }
            .map { line -> line.split(" ").let { it[0] to it[1].toInt() } }
            .map { (direction, amount) ->
                when (direction) {
                    "forward" -> Movement(horizontal = amount)
                    "up" -> Movement(depth = -amount)
                    "down" -> Movement(depth = amount)
                    else -> error("invalid input: $direction")
                }
            }
            .fold(Movement(0, 0), Movement::plus)

        println(position)
        println(position.depth * position.horizontal)
        return position.depth * position.horizontal
    }
}

fun main() {
    Day2.part1(generateSequence(::readLine))
}
