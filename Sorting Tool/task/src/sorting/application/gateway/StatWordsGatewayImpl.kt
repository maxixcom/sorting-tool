package sorting.application.gateway

import sorting.domain.entity.StatWords
import sorting.domain.gateway.StatGateway

class StatWordsGatewayImpl : StatGateway<StatWords> {
    override fun buildStat(input: List<String>): StatWords {
        val words = input
            .flatMap {
                it.split("\\s+".toRegex())
            }
        val index = words
            .groupingBy { it }
            .fold(0) { acc, _ -> acc + 1 }

        val longestWord = index.keys.maxByOrNull { it.length }

        return StatWords(
            totalCount = words.size,
            longestWord = longestWord ?: "",
            longestWordCount = index[longestWord] ?: 0
        )
    }
}
