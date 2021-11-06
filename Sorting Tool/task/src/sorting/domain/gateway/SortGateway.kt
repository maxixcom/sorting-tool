package sorting.domain.gateway

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem

interface SortGateway {
    fun sortLongs(input: List<String>, sortBy: SortBy): List<StatItem<Long>>
    fun sortWords(input: List<String>, sortBy: SortBy): List<StatItem<String>>
    fun sortLines(input: List<String>, sortBy: SortBy): List<StatItem<String>>
}
