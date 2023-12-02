import aoc2021.Day6
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day06Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day06/sample.txt")
            Day6.part1(sample) shouldBe 5934
        }

        test("sample2") {
            val sample = loadAsText("/day06/sample.txt")
            Day6.part2(sample) shouldBe 26984457539
        }

        test("test input") {
            val sample = loadAsText("/day06/input.txt")
            Day6.part1(sample) shouldBe 380243
            Day6.part2(sample) shouldBe 1708791884591L
        }
    }
)