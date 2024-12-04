package day04

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        for (r in input.indices) {
            for (c in input[0].indices) {
                if (input[r][c] != 'X') continue
                for (dr in -1..1) {
                    for (dc in -1..1) {
                        if (dr == 0 && dc == 0) continue
                        if (r + 3 * dr !in 0..input.lastIndex || c + 3 * dc !in 0..input.lastIndex) continue
                        if (input[r + dr][c + dc] == 'M' &&
                            input[r + 2 * dr][c + 2 * dc] == 'A' &&
                            input[r + 3 * dr][c + 3 * dc] == 'S'
                        ) {
                            count++
                        }
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (r in 1..<input.lastIndex) {
            for (c in 1..<input[0].lastIndex) {
                if (input[r][c] != 'A') continue
                val corners = listOf(input[r - 1][c - 1], input[r - 1][c + 1], input[r + 1][c + 1], input[r + 1][c - 1])
                if (corners.joinToString("") in listOf("MMSS", "MSSM", "SSMM", "SMMS")) count++
            }
        }

        return count
    }

    val testInput = readInput("day04/Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("day04/Day04")
    part1(input).println()
    part2(input).println()
}