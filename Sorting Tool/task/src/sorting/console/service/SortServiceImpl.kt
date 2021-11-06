package sorting.console.service

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem
import sorting.domain.usecase.SortLines
import sorting.domain.usecase.SortLong
import sorting.domain.usecase.SortWords

class SortServiceImpl(
    private val useCaseSortLong: SortLong,
    private val useCaseSortWords: SortWords,
    private val useCaseSortLines: SortLines,

) : SortService {

    override fun sortLongs(lines: List<String>, sortBy: SortBy): Result<List<StatItem<Long>>> {
        val request = SortLong.Request(lines, sortBy)
        return useCaseSortLong.execute(request).result
    }

    override fun sortWords(lines: List<String>, sortBy: SortBy): Result<List<StatItem<String>>> {
        val request = SortWords.Request(lines, sortBy)
        return useCaseSortWords.execute(request).result
    }

    override fun sortLines(lines: List<String>, sortBy: SortBy): Result<List<StatItem<String>>> {
        val request = SortLines.Request(lines, sortBy)
        return useCaseSortLines.execute(request).result
    }
}
