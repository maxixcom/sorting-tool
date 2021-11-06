package sorting.console

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem
import java.util.Scanner

class ConsoleRunner(
    private val args: Array<String>
) : Runnable {
    private val sortService = Application.sortService
    private lateinit var dataType: DataType
    private lateinit var sortBy: SortBy

    private fun parseSortingType(args: Array<String>) {
        val index = args.indexOf("-sortingType")
        if (index != -1) {
            if (index + 1 >= args.size) {
                println("No sorting type defined!")
                return
            }
            sortBy = when (args[index + 1]) {
                "byCount" -> SortBy.ByCount
                else -> SortBy.Natural
            }
        }
    }

    private fun parseDataType(args: Array<String>) {
        val index = args.indexOf("-dataType")
        if (index != -1) {
            if (index + 1 >= args.size) {
                println("No data type defined!")
                return
            }
            dataType = when (args[index + 1]) {
                DataType.Lines.arg -> DataType.Lines
                DataType.Long.arg -> DataType.Long
                else -> DataType.Words
            }
        }
    }

    private fun parseUnknownOptions(args: Array<String>) {
        args
            .filter { it.indexOf("-") != -1 }
            .filter { it != "-dataType" && it != "-sortingType" }
            .forEach {
                println("\"$it\" is not a valid parameter. It will be skipped.")
            }
    }

    override fun run() {
        try {
            parseSortingType(args)
            parseDataType(args)
            parseUnknownOptions(args)
            if (!this::dataType.isInitialized) {
                this.dataType = DataType.Words
            }
            if (!this::sortBy.isInitialized) {
                this.sortBy = SortBy.Natural
            }
        } catch (e: Exception) {
            return
        }
        val s = Scanner(System.`in`)
        val input = mutableListOf<String>()
        while (s.hasNext()) {
            input.add(s.nextLine())
        }

        when (dataType) {
            DataType.Long -> this.runSortLongs(input)
            DataType.Lines -> this.runSortLines(input)
            DataType.Words -> this.runSortWords(input)
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
