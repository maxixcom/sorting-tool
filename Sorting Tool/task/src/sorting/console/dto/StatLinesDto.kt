package sorting.console.dto

import sorting.domain.entity.StatLines

class StatLinesDto(
    val totalCount: Int,
    val longestLine: String,
    val longestLineCount: Int,
    val longestLinePercent: Int,
)

fun StatLines.toStatLinesDto() =
    StatLinesDto(
        totalCount = totalCount,
        longestLine = longestLine,
        longestLineCount = longestLineCount,
        longestLinePercent = if (totalCount > 0) longestLineCount * 100 / totalCount else 0
    )
