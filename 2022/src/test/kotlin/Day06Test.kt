import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day06Test : FunSpec(
    {
        context("samples") {
            withData(
                "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
                "nppdvjthqldpwncqszvftbrmjlhg" to 6,
                "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
                "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11
            ) { (input, expected) ->
                Day6.part1(input) shouldBe expected
            }
        }

        test("test input") {
            val sample = loadAsText("/day06/input.txt")
            Day6.part1(sample) shouldBe 1723
            Day6.part2(sample) shouldBe 3708
        }
    }
)