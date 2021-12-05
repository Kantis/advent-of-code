import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day05Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day05/sample.txt")
            Day5.part1(sample.lineSequence()) shouldBe 5
        }

        test("sample2") {
            val sample = loadAsText("/day05/sample.txt")
            Day5.part2(sample.lineSequence()) shouldBe 12
        }

        test("test input") {
            val sample = loadAsText("/day05/input.txt")
            Day5.part1(sample.lineSequence()) shouldBe 5169
            Day5.part2(sample.lineSequence()) shouldBe 22083
        }
    }
)
