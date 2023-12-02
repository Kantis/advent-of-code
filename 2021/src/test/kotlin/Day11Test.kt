import aoc2021.Day11
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day11/sample.txt")
            Day11.part1(sample, 1) shouldBe 9
            Day11.part1(sample, 2) shouldBe 9
            Day11.part1(loadAsText("/day11/sample2.txt"), 100) shouldBe 1656
        }

        test("sample2") {
            val sample = loadAsText("/day11/sample2.txt")
            Day11.part2(sample) shouldBe 195
        }

        test("test input") {
            val sample = loadAsText("/day11/input.txt")
            Day11.part1(sample, 100) shouldBe 1732
            Day11.part2(sample) shouldBe 2
        }
    }
)