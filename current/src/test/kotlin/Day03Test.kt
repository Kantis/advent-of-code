import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.system.measureTimeMillis

class Day03Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day03/sample.txt")
            Day3.part1(sample) shouldBe 4361
        }

        test("sample2") {
            val sample = loadAsText("/day03/sample.txt")
            Day3.part2(sample) shouldBe 467835
        }

        test("test input") {
            val sample = loadAsText("/day03/input.txt")
            measureTimeMillis {
                Day3.part1(sample) shouldBe 533784
                Day3.part2(sample) shouldBe 78826761
            }.also { println("Time: $it ms") }
        }

        test("some gear") {
            Day3.part2(
                """
                ..837..
                .....*.
                ..287..
                """.trimIndent(),
            ) shouldBe 837 * 287
        }
    },
)
