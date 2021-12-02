object Day2 {
    sealed interface Command {
        val amount: Int
    }

    data class Forward(override val amount: Int) : Command
    data class ChangeAim(override val amount: Int) : Command

    data class Position(
        val horizontal: Int = 0,
        val depth: Int = 0,
        val aim: Int = 0,
    ) {
        operator fun invoke(cmd: Command) =
            when (cmd) {
                is Forward -> this.copy(horizontal + cmd.amount, depth + cmd.amount * aim)
                is ChangeAim -> this.copy(aim = aim + cmd.amount)
            }
    }

    fun part1(lines: Sequence<String>): Int {
        val position = lines
            .filterNot { it.isBlank() }
            .map { line -> line.split(" ").let { it[0] to it[1].toInt() } }
            .map { (direction, amount) ->
                when (direction) {
                    "forward" -> Forward(amount)
                    "up" -> ChangeAim(-amount)
                    "down" -> ChangeAim(amount)
                    else -> error("invalid input: $direction")
                }
            }
            .fold(Position(0, 0), Position::invoke)

        println(position)
        println(position.depth * position.horizontal)
        return position.depth * position.horizontal
    }
}

fun main() {
    Day2.part1(generateSequence(::readLine))
}
