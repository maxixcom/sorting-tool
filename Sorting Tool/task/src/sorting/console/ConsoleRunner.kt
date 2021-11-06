package sorting.console

import sorting.console.io.Printer
import sorting.console.io.PrinterFile
import sorting.console.io.PrinterStdout
import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem
import java.io.File
import java.util.Scanner

class ConsoleRunner(
    private val args: Array<String>
) : Runnable {
    private val sortService = Application.sortService
    private lateinit var dataType: DataType
    private lateinit var sortBy: SortBy
    private lateinit var printer: Printer
    private lateinit var inputFile: File

    private fun parseInputFile(args: Array<String>) {
        val index = args.indexOf("-inputFile")
        if (index != -1) {
            if (index + 1 >= args.size) {
                throw Exception("No input file provided!")
            }
            inputFile = File(args[index + 1])
        }
    }

    private fun parseOutputFile(args: Array<String>) {
        val index = args.indexOf("-outputFile")
        printer = if (index != -1) {
            if (index + 1 >= args.size) {
                throw Exception("No output file provided!")
            }
            PrinterFile(File(args[index + 1]))
        } else {
            PrinterStdout()
        }
    }

    private fun parseSortingType(args: Array<String>) {
        val index = args.indexOf("-sortingType")
        sortBy = if (index != -1) {
            if (index + 1 >= args.size) {
                throw Exception("No sorting type defined!")
            }
            when (args[index + 1]) {
                "byCount" -> SortBy.ByCount
                else -> SortBy.Natural
            }
        } else {
            SortBy.Natural
        }
    }

    private fun parseDataType(args: Array<String>) {
        val index = args.indexOf("-dataType")
        dataType = if (index != -1) {
            if (index + 1 >= args.size) {
                throw Exception("No data type defined!")
            }
            when (args[index + 1]) {
                DataType.Lines.arg -> DataType.Lines
                DataType.Long.arg -> DataType.Long
                else -> DataType.Words
            }
        } else {
            DataType.Words
        }
    }

    private fun parseUnknownOptions(args: Array<String>) {
        val options = listOf(
            "-dataType",
            "-sortingType",
            "-inputFile",
            "-outputFile",
        )
        args
            .filter { it.indexOf("-") != -1 }
            .filter { it !in options }
            .forEach {
                println("\"$it\" is not a valid parameter. It will be skipped.")
            }
    }

    override fun run() {
        try {
            parseSortingType(args)
            parseDataType(args)
            parseUnknownOptions(args)
            parseInputFile(args)
            parseOutputFile(args)
        } catch (e: Exception) {
            println(e.message)
            return
        }

        val input = if (this::inputFile.isInitialized) {
            this.inputFile.readLines()
        } else {
            val s = Scanner(System.`in`)
            val tmp = mutableListOf<String>()
            while (s.hasNext()) {
                tmp.add(s.nextLine())
            }
            tmp
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
                { printer.println(it.message) }
            )
        else {
            result.fold(
                { printSortedByNaturalResult("number", " ", it) },
                { printer.println(it.message) }
            )
        }
    }

    private fun runSortWords(lines: List<String>) {
        val result = sortService.sortWords(lines, sortBy)
        if (sortBy == SortBy.ByCount)
            result.fold(
                { printSortedByCountResult("word", it) },
                { printer.println(it.message) }
            )
        else {
            result.fold(
                { printSortedByNaturalResult("word", " ", it) },
                { printer.println(it.message) }
            )
        }
    }

    private fun runSortLines(lines: List<String>) {
        val result = sortService.sortLines(lines, sortBy)
        if (sortBy == SortBy.ByCount)
            result.fold(
                { printSortedByCountResult("line", it) },
                { printer.println(it.message) }
            )
        else {
            result.fold(
                { printSortedByNaturalResult("line", "\n", it) },
                { printer.println(it.message) }
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

        printer.println("Total ${title}s: $total.")

        invertedIndex.keys.sortedBy { it }
            .forEach { count ->
                invertedIndex[count]!!.forEach {
                    printer.println("$it: $count time(s), ${count * 100 / total}%")
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
        printer.println("Total ${title}s: $total.")
        printer.println("Sorted data: ${items.joinToString(separator)}")
    }
}
