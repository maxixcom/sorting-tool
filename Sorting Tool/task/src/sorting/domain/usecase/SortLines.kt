package sorting.domain.usecase

import sorting.domain.entity.SortBy
import sorting.domain.entity.StatItem

interface SortLines {
    fun execute(request: Request): Response
    data class Request(
        val input: List<String>,
        val sortBy: SortBy,
    )

    data class Response(val result: Result<List<StatItem<String>>>)
}
