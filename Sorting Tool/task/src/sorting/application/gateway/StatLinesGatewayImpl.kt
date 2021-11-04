package sorting.application.gateway

import sorting.domain.entity.StatLines
import sorting.domain.gateway.StatGateway

class StatLinesGatewayImpl : StatGateway<StatLines> {
    override fun buildStat(input: List<String>): StatLines {
        val index = input
            .groupingBy { it }
            .fold(0) { acc, _ -> acc + 1 }

        val longestLine = index.keys.maxByOrNull { it.length }

        return StatLines(
            totalCount = input.size,
            longestLine = longestLine ?: "",
            longestLineCount = index[longestLine] ?: 0
        )
    }
}
