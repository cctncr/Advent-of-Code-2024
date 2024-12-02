package day02

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { line -> line.split(" ").map { it.toInt() } }
            .map { nums -> nums.zipWithNext { a, b -> a - b } }
            .count { diffs -> diffs.all { it in 1..3 } || diffs.all { it in -3..-1 } }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { line -> line.split(" ").map { it.toInt() } }
            .count { nums ->
                nums.indices.any { index ->
                    val reduced = nums.toMutableList().apply { removeAt(index) }
                    val diffs = reduced.zipWithNext { a, b -> a - b }
                    diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }
                }
            }
    }

    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("day02/Day02")
    part1(input).println()
    part2(input).println()
}