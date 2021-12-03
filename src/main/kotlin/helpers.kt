fun Int.pow(exponent: Int): Long =
    (0 until exponent).map { this.toLong() }.reduce(Long::times)

fun String.binaryToInt() = Integer.parseInt(this, 2)
