import aoc2021.Day14
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day14Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day14/sample.txt")
            Day14.part1(sample) shouldBe 1588
        }

        test("sample2") {
            val sample = loadAsText("/day14/sample.txt")
            Day14.part2(sample) shouldBe 2
        }

        test("test input") {
            val sample = loadAsText("/day14/input.txt")
            Day14.part1(sample) shouldBe 2988
            Day14.part2(sample) shouldBe 2
        }
    }
)