package sorting.console

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem
import java.util.Scanner

class ConsoleRunner(args: Array<String>) : Runnable {
    private val sortService = Application.sortService
    private lateinit var statType: StatType
    private lateinit var sortBy: SortBy

    init {
        parseArguments(args)
    }

    private fun parseArguments(args: Array<String>) {
        val argSortingTypeIndex = args.indexOf("-sortingType")
        this.sortBy = if (argSortingTypeIndex != -1 && argSortingTypeIndex + 1 < args.size) {
            when (args[argSortingTypeIndex + 1]) {
                "byCount" -> SortBy.ByCount
                else -> SortBy.Natural
            }
        } else {
            SortBy.Natural
        }
        val argDataTypeIndex = args.indexOf("-dataType")
        this.statType = if (argDataTypeIndex != -1 && argDataTypeIndex + 1 < args.size) {
            when (args[argDataTypeIndex + 1]) {
                StatType.Lines.arg -> StatType.Lines
                StatType.Long.arg -> StatType.Long
                else -> StatType.Words
            }
        } else {
            StatType.Words
        }
    }

    override fun run() {

        val s = Scanner(System.`in`)
        val input = mutableListOf<String>()
        while (s.hasNext()) {
            input.add(s.nextLine())
        }

        when (statType) {
            StatType.Long -> this.runSortLongs(input)
            StatType.Lines -> this.runSortLines(input)
            StatType.Words -> this.runSortWords(input)
        }
    }

    private fun runSortLongs(lines: List<String>) {
        val result = sortService.sortLongs(lines, sortBy)
        if (sortBy == SortBy.ByCount)
            result.fold(
                { printSortedByCountResult("number", it) },
                { println(it.message) }
            )
        else {
            result.fold(
                { printSortedByNaturalResult("number", " ", it) },
                { println(it.message) }
            )
        }
    }

    private fun runSortWords(lines: List<String>) {
        val result = sortService.sortWords(lines, sortBy)
        if (sortBy == SortBy.ByCount)
            result.fold(
                { printSortedByCountResult("word", it) },
                { println(it.message) }
            )
        else {
            result.fold(
                { printSortedByNaturalResult("word", " ", it) },
                { println(it.message) }
            )
        }
    }

    private fun runSortLines(lines: List<String>) {
        val result = sortService.sortLines(lines, sortBy)
        if (sortBy == SortBy.ByCount)
            result.fold(
                { printSortedByCountResult("line", it) },
                { println(it.message) }
            )
        else {
            result.fold(
                { printSortedByNaturalResult("line", "\n", it) },
                { println(it.message) }
            )
        }
    }

    private fun <T : Comparable<T>> printSortedByCountResult(
        title: String,
        list: List<StatItem<T>> = emptyList()
    ) {
        val total = list.sumOf { it.count }

        val invertedIndex = list.groupBy {
            it.count
        }.map { entry ->
            entry.key to entry.value.map { it.value }.sortedBy { it }
        }.toMap()

        println("Total ${title}s: $total.")

        invertedIndex.keys.sortedBy { it }
            .forEach { count ->
                invertedIndex[count]!!.forEach {
                    println("$it: $count time(s), ${count * 100 / total}%")
                }
            }
    }

    private fun <T> printSortedByNaturalResult(
        title: String,
        separator: String = " ",
        list: List<StatItem<T>> = emptyList()
    ) {
        val total = list.sumOf { it.count }
        val items = list.flatMap { statItem ->
            List(statItem.count) { statItem.value }
        }
        println("Total ${title}s: $total.")
        println("Sorted data: ${items.joinToString(separator)}")
    }
}
