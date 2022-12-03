import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day16Test : FunSpec(
    {
        test("hex2bin") {
            "FF".hexToBin() shouldBe "11111111"
            "01".hexToBin() shouldBe "00000001"
        }

        test("parse literal") {
            parsePacket("D2FE28".hexToBin()) shouldBe Pair(LiteralPacket(6, 2021), 21)
            parsePacket("11010001010") shouldBe Pair(LiteralPacket(6, 10), 11)
            parsePacket("1101000101001010010001001000000000") shouldBe Pair(LiteralPacket(6, 10), 11)
        }

        test("Operator packet with length mode 0") {
            parsePacket("EE00D40C823060".hexToBin()) shouldBe Pair(
                OpPacket(
                    7,
                    3,
                    Length.NumberOfPackets(3),
                    listOf(LiteralPacket(2, 1), LiteralPacket(4, 2), LiteralPacket(1, 3))
                ),
                51
            )
        }

        test("Op packet with length mode 1") {
            parsePacket("38006F45291200".hexToBin()) shouldBe Pair(
                OpPacket(1, 6, Length.TotalBits(27), listOf(LiteralPacket(6, 10), LiteralPacket(2, 20))),
                49
            )
        }

        test("sample") {
            Day16.part1("8A004A801A8002F478") shouldBe 16
            Day16.part1("620080001611562C8802118E34") shouldBe 12
            Day16.part1("C0015000016115A2E0802F182340") shouldBe 23
            Day16.part1("A0016C880162017C3686B18A3D4780") shouldBe 31
        }

        test("sample2") {
            Day16.part2("C200B40A82") shouldBe 3
            Day16.part2("04005AC33890") shouldBe 54
            Day16.part2("880086C3E88112") shouldBe 7
            Day16.part2("CE00C43D881120") shouldBe 9
            Day16.part2("D8005AC2A8F0") shouldBe 1
            Day16.part2("F600BC2D8F") shouldBe 0
            Day16.part2("9C005AC2F8F0") shouldBe 0
            Day16.part2("9C0141080250320F1802104A08") shouldBe 1
        }

        test("test input") {
            val sample = loadAsText("/day16/input.txt")
            Day16.part1(sample) shouldBe 955
            Day16.part2(sample) shouldBe 2
        }
    }
)