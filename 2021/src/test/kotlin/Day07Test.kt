import aoc2021.Day7
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day07Test : FunSpec(
    {
        xtest("sample") {
            val sample = loadAsText("/day07/sample.txt")
            Day7.part1(sample) shouldBe 2
        }

        xtest("sample2") {
            val sample = loadAsText("/day07/sample.txt")
            Day7.part2(sample) shouldBe 168
        }

        xtest("test input") {
            val sample = loadAsText("/day07/input.txt")
//            Day7.part1(sample) shouldBe 1
            Day7.part2(sample) shouldBe 2
        }
    }
)