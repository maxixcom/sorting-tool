package sorting.console

import java.util.Scanner

class ConsoleRunner(args: Array<String>) : Runnable {
    private val statService = Application.statService
    private val statType: StatType

    init {
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
            StatType.Long -> {
                statService.buildStatLong(input).fold(
                    { stat ->
                        println("Total numbers: ${stat.totalCount}.")
                        println("The greatest number: ${stat.maxLong} (${stat.maxCount} time(s), ${stat.maxPercent}%).")
                    },
                    {
                        println("Error: ${it.message}")
                    }
                )
            }
            StatType.Lines -> {
                statService.buildStatLines(input).fold(
                    { stat ->
                        println("Total lines: ${stat.totalCount}.")
                        println("The longest line:")
                        println("${stat.longestLine}")
                        println("(${stat.longestLineCount} time(s), ${stat.longestLinePercent}%).")
                    },
                    {
                        println("Error: ${it.message}")
                    }
                )
            }
            StatType.Words -> {
                statService.buildStatWords(input).fold(
                    { stat ->
                        println("Total words: ${stat.totalCount}.")
                        println("The longest word: ${stat.longestWord} (${stat.longestWordCount} time(s), ${stat.longestWordPercent}%).")
                    },
                    {
                        println("Error: ${it.message}")
                    }
                )
            }
        }
    }
}
