package sorting.console.service

import sorting.console.dto.StatLinesDto
import sorting.console.dto.StatLongDto
import sorting.console.dto.StatWordsDto
import sorting.console.dto.toStatLinesDto
import sorting.console.dto.toStatLongDto
import sorting.console.dto.toStatWordsDto
import sorting.domain.usecase.BuildStatLines
import sorting.domain.usecase.BuildStatLong
import sorting.domain.usecase.BuildStatWords

class StatServiceImpl(
    private val useCaseBuildStatLong: BuildStatLong,
    private val useCaseBuildStatLines: BuildStatLines,
    private val useCaseBuildStatWords: BuildStatWords
) : StatService {
    override fun buildStatLong(lines: List<String>): Result<StatLongDto> {
        return runCatching {
            val request = BuildStatLong.Request(
                input = lines
            )
            useCaseBuildStatLong.execute(request).result.getOrThrow().toStatLongDto()
        }
    }

    override fun buildStatLines(lines: List<String>): Result<StatLinesDto> {
        return runCatching {
            val request = BuildStatLines.Request(
                input = lines
            )
            useCaseBuildStatLines.execute(request).result.getOrThrow().toStatLinesDto()
        }
    }

    override fun buildStatWords(lines: List<String>): Result<StatWordsDto> {
        return runCatching {
            val request = BuildStatWords.Request(
                input = lines
            )
            useCaseBuildStatWords.execute(request).result.getOrThrow().toStatWordsDto()
        }
    }
}
