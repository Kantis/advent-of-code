import aoc2021.day13
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day13Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day13/sample.txt")
            day13.part1(sample) shouldBe 17
        }

        test("sample2") {
            val sample = loadAsText("/day13/sample.txt")
            day13.part2(sample)
        }

        test("test input") {
            val sample = loadAsText("/day13/input.txt")
            day13.part1(sample) shouldBe 655
            day13.part2(sample)
        }
    }
)
