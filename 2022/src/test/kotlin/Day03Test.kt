import Day3.priority
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day03Test : FunSpec(
    {
        context("priority") {
            withData(
                'a' to 1,
                'z' to 26,
                'A' to 27,
                'Z' to 52,
            ) { (a, expected) ->
                priority(a) shouldBe expected
            }
        }

        context("Compartmentalize") {
            withData(
                "vJrwpWtwJgWrhcsFMMfFFhFp" to Pair("vJrwpWtwJgWr", "hcsFMMfFFhFp"),
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" to Pair("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"),
            ) { (rucksack, expected) ->
                Day3.compartmentalize(rucksack) shouldBe expected
            }
        }

        test("sample") {
            val sample = loadAsText("/day03/sample.txt")
            Day3.part1(sample) shouldBe 157
        }

        test("sample2") {
            val sample = loadAsText("/day03/sample.txt")
            Day3.part2(sample) shouldBe 70
        }

        test("test input") {
            val sample = loadAsText("/day03/input.txt")
            Day3.part1(sample) shouldBe 7821
            Day3.part2(sample) shouldBe 2752
        }
    }
)