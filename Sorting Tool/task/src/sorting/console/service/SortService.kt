package sorting.console.service

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem

interface SortService {
    fun sortLongs(lines: List<String>, sortBy: SortBy): Result<List<StatItem<Long>>>
    fun sortWords(lines: List<String>, sortBy: SortBy): Result<List<StatItem<String>>>
    fun sortLines(lines: List<String>, sortBy: SortBy): Result<List<StatItem<String>>>
}
