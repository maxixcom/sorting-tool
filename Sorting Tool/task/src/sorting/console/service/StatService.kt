package sorting.console.service

import sorting.console.dto.StatLinesDto
import sorting.console.dto.StatLongDto
import sorting.console.dto.StatWordsDto

interface StatService {
    fun buildStatLong(lines: List<String>): Result<StatLongDto>
    fun buildStatLines(lines: List<String>): Result<StatLinesDto>
    fun buildStatWords(lines: List<String>): Result<StatWordsDto>
}
