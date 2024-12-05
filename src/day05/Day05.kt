package day05

import println
import readInput

fun main() {
    fun getRulesAndUpdatesPair(input: List<String>): Pair<MutableMap<Int, List<Int>>, MutableList<List<Int>>> {
        val rules = mutableMapOf<Int, List<Int>>()
        val updates = mutableListOf<List<Int>>()
        var isRuleFlag = true
        input.forEach { line ->
            if (isRuleFlag) {
                if (line.isEmpty()) {
                    isRuleFlag = false
                } else {
                    line.split("|").let {
                        val firstNum = it.first().toInt()
                        val secondNum = it.last().toInt()
                        rules[firstNum] = if (rules[firstNum] == null) {
                            arrayListOf(secondNum)
                        } else {
                            rules[firstNum]!!.toMutableList().apply { add(secondNum) }
                        }
                    }
                }
            } else {
                updates.add(line.split(',').map { it.toInt() })
            }
        }
        return Pair(rules, updates)
    }

    fun isUpdateCorrect(update: List<Int>, rules: Map<Int, List<Int>>): Boolean {
        var isCorrect = true
        update.forEachIndexed { index, num ->
            val mustBeforeNums = rules[num]
            for (i in 0..index) {
                if (mustBeforeNums?.contains(update[i]) == true) {
                    isCorrect = false
                }
            }
            if (!isCorrect) return@forEachIndexed
        }
        return isCorrect
    }

    fun getUpdates(order: Order, updates: List<List<Int>>, rules: Map<Int, List<Int>>): List<List<Int>> {
        return buildList {
            updates.forEach {
                val isCorrect = isUpdateCorrect(it, rules)
                when (order) {
                    Order.CORRECT -> if (isCorrect) add(it)
                    Order.WRONG -> if (!isCorrect) add(it)
                }
            }
        }
    }

    // semi bubble sort
    fun sortedUpdate(update: List<Int>, rules: Map<Int, List<Int>>): List<Int> {
        val newUpdate = update.toMutableList()
        for (i in 0..newUpdate.lastIndex) {
            for (j in 0 until newUpdate.lastIndex - i) {
                if (rules[newUpdate[j]]?.contains(newUpdate[j + 1]) == true) {
                    val temp = newUpdate[j]
                    newUpdate[j] = newUpdate[j + 1]
                    newUpdate[j + 1] = temp
                }
            }
        }
        return newUpdate
    }

    fun part1(input: List<String>): Int {
        val (rules, updates) = getRulesAndUpdatesPair(input)
        val correctUpdates = getUpdates(Order.CORRECT, updates, rules)
        return correctUpdates.sumOf { it[it.lastIndex / 2] }
    }

    fun part2(input: List<String>): Int {
        val (rules, updates) = getRulesAndUpdatesPair(input)
        val wrongUpdates = getUpdates(Order.WRONG, updates, rules)

        return wrongUpdates.sumOf {
            val sorted = sortedUpdate(it, rules)
            sorted[it.lastIndex / 2]
        }
    }

    val testInput = readInput("day05/Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("day05/Day05")
    part1(input).println()
    part2(input).println()
}

enum class Order { CORRECT, WRONG }
