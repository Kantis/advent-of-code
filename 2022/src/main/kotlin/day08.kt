typealias Grid<T> = List<List<T>>

fun <T, R> Grid<T>.gridMapIndexed(fn: (x: Int, y: Int, T) -> R): Grid<R> =
    mapIndexed { y, ts ->
        ts.mapIndexed { x, t -> fn(x, y, t) }
    }

fun <T, R> Grid<T>.gridMap(fn: (T) -> R): Grid<R> =
    map { ts -> ts.map(fn) }

fun List<String>.toCharGrid(): Grid<Char> = map { it.map(::identity) }

@JvmInline
value class Tree(val height: Int)

fun Char.toTree() = Tree(digitToInt())

object Day8 {

    fun Tree.isVisible(requiredHeight: Int) = height > requiredHeight

    fun List<String>.toTrees(): Grid<Tree> = map { line -> line.map(Char::toTree) }

    fun part1(input: String): Int =
        input.lines()
            .toCharGrid()
            .gridMap(Char::toTree)
            .gridMapIndexed { x, y, tree ->  }


    fun part2(input: String): Int {
        return 2
    }
}
