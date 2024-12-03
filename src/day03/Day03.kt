package day03

import println
import readInput

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { line ->
            val matches = """mul\(\d+,\d+\)""".toRegex().findAll(line)
            matches.sumOf { matchResult ->
                val (first, second) = matchResult.value.removeSurrounding("mul(", ")").split(",")
                first.toLong() * second.toLong()
            }
        }
    }

    fun part2(input: List<String>): Long {
        var doFlag = true
        var count = 0L
        input.forEach { line ->
            val matches = """mul\(\d+,\d+\)|do(n't)?\(\)""".toRegex().findAll(line)
            matches.forEach { matchResult ->
                when (matchResult.value) {
                    "do()" -> doFlag = true
                    "don't()" -> doFlag = false
                    else -> {
                        if (doFlag) {
                            val (first, second) =
                                matchResult.value.removeSurrounding("mul(", ")").split(",")
                            count += first.toLong() * second.toLong()
                        }
                    }
                }
            }
        }
        return count
    }

    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 161L)
    check(part2(testInput) == 48L)

    val input = readInput("day03/Day03")
    part1(input).println()
    part2(input).println()
}