package sorting.console.dto

import sorting.domain.entity.StatWords

class StatWordsDto(
    val totalCount: Int,
    val longestWord: String,
    val longestWordCount: Int,
    val longestWordPercent: Int,
)

fun StatWords.toStatWordsDto() =
    StatWordsDto(
        totalCount = totalCount,
        longestWord = longestWord,
        longestWordCount = longestWordCount,
        longestWordPercent = if (totalCount > 0) longestWordCount * 100 / totalCount else 0
    )
