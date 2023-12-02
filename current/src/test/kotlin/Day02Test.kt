import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day02/sample.txt")
            Day2.part1(sample) shouldBe 8
        }

        test("sample2") {
            val sample = loadAsText("/day02/sample.txt")
            Day2.part2(sample) shouldBe 2286
        }

        test("test input") {
            val sample = loadAsText("/day02/input.txt")
            Day2.part1(sample) shouldBe 2237
            Day2.part2(sample) shouldBe 66681
        }
    }
)