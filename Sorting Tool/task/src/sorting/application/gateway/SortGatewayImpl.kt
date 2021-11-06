package sorting.application.gateway

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem
import sorting.domain.gateway.SortGateway

class SortGatewayImpl : SortGateway {
    override fun sortLongs(input: List<String>, sortBy: SortBy): List<StatItem<Long>> {
        val regex = "-?\\d+".toRegex()

        val numbers = input.flatMap { line ->
            line.trim().split("\\s+".toRegex())
                .filter {
                    if (regex.matches(it)) {
                        true
                    } else {
                        println("\"$it\" is not a valid parameter. It will be skipped.")
                        false
                    }
                }
                .map(String::toLong)
        }

        return sortItems(numbers, sortBy)
    }

    override fun sortWords(input: List<String>, sortBy: SortBy): List<StatItem<String>> {
        val words = input
            .map { it.trim() }
            .flatMap {
                it.split("\\s+".toRegex())
            }
        return sortItems(words, sortBy)
    }

    override fun sortLines(input: List<String>, sortBy: SortBy): List<StatItem<String>> {
        return sortItems(input, sortBy)
    }

    private fun <T : Comparable<T>> sortItems(items: List<T>, sortBy: SortBy): List<StatItem<T>> {
        val index = items.groupingBy { it }
            .fold(0) { acc, _ -> acc + 1 }
            .map {
                StatItem(
                    value = it.key,
                    count = it.value
                )
            }

        if (sortBy == SortBy.ByCount) {
            return index.sortedBy { it.count }
        }
        return index.sortedBy { it.value }
    }
}
