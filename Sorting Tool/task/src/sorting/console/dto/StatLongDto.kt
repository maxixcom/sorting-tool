package sorting.console.dto

import sorting.domain.entity.StatLong

data class StatLongDto(
    val totalCount: Int,
    val maxLong: Long,
    val maxCount: Int,
    val maxPercent: Int,
)

fun StatLong.toStatLongDto() =
    StatLongDto(
        totalCount = totalCount,
        maxLong = maxLong,
        maxCount = maxCount,
        maxPercent = if (totalCount > 0) maxCount * 100 / totalCount else 0
    )
