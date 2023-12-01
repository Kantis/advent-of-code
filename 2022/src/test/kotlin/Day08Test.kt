import Day8.countVisible
import Day8.transpose
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Test : FunSpec(
    {
        test("Transpose") {
            listOf(
                "hello",
                "world"
            ).transpose() shouldBe listOf(
                "hw",
                "eo",
                "lr",
                "ll",
                "od"
            )
        }

        test("Dummy") {
            "30373".map(Char::digitToInt).countVisible() shouldBe 2
            "33549".map(Char::digitToInt).countVisible() shouldBe 3
        }

        test("sample") {
            val sample = loadAsText("/day08/sample.txt")
            Day8.part1(sample) shouldBe 21
        }

        test("sample2") {
            val sample = loadAsText("/day08/sample.txt")
            Day8.part2(sample) shouldBe 2
        }

        test("test input") {
            val sample = loadAsText("/day08/input.txt")
            Day8.part1(sample) shouldBe 1
            Day8.part2(sample) shouldBe 2
        }
    }
)