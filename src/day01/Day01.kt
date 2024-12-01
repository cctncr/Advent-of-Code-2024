package day01

import println
import readInput
import kotlin.math.abs

fun main() {
    fun getLists(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()
        input.forEach {
            val (first, second) = it.split("\\s+".toRegex())
            firstList.add(first.toInt())
            secondList.add(second.toInt())
        }
        return Pair(firstList, secondList)
    }

    fun part1(input: List<String>): Int {
        val (firstList, secondList) = getLists(input)
        firstList.sort()
        secondList.sort()
        var result = 0
        firstList.zip(secondList) { first, second ->
            result += abs(first - second)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val (firstList, secondList) = getLists(input)
        var result = 0
        firstList.forEach { value ->
            result += value * secondList.count { it == value }
        }
        return result
    }

    val testInput = readInput("Day01/Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("day01/Day01")
    part1(input).println()
    part2(input).println()
}