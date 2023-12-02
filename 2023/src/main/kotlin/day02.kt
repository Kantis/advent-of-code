object Day2 {
    data class Round(val id: Int, val shownCubes: List<List<Cube>>)
    data class Cube(val count: Int, val color: CubeColor)

    enum class CubeColor { RED, BLUE, GREEN, }

    fun parseRound(line: String): Round =
        line.split(": ").let { (round, shownCubes) ->
            Round(
                round.drop(5).toInt(),
                shownCubes.split("; ").map(::parseShownCubes),
            )
        }

    fun parseShownCubes(shownCubes: String): List<Cube> = shownCubes.split(", ").map(::parseCube)

    fun parseCube(cube: String): Cube = cube.split(" ").let { (count, color) ->
        Cube(count.toInt(), CubeColor.valueOf(color.uppercase()))
    }

    fun Round.maxByColor(color: CubeColor): Int =
        shownCubes.mapNotNull { cubes ->
            cubes.find { it.color == color }?.count
        }.maxOrNull() ?: 0

    fun part1(input: String): Int {
        return input.lines().map(::parseRound).filter { round ->
            val red = round.maxByColor(CubeColor.RED)
            val green = round.maxByColor(CubeColor.GREEN)
            val blue = round.maxByColor(CubeColor.BLUE)
            red <= 12 && green <= 13 && blue <= 14
        }.sumOf(Round::id)
    }

    fun part2(input: String): Int {
        return input.lines().map(::parseRound).sumOf { round ->
            CubeColor.entries.map { round.maxByColor(it) }.reduce(Int::times)
        }
    }
}
