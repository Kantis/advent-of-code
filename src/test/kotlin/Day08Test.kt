import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day08/sample.txt")
            Day8.part1(sample.lineSequence()) shouldBe 26
        }

        test("sample2") {
            val sample = loadAsText("/day08/sample.txt")
            Day8.part2(sample.lineSequence()) shouldBe 61229
        }

        test("test input") {
            val sample = loadAsText("/day08/input.txt")
            Day8.part1(sample.lineSequence()) shouldBe 548
            Day8.part2(sample.lineSequence()) shouldBe 1074888
        }
    }
)
