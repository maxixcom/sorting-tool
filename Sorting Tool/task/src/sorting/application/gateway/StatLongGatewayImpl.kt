package sorting.application.gateway

import sorting.domain.entity.StatLong
import sorting.domain.gateway.StatGateway

class StatLongGatewayImpl : StatGateway<StatLong> {
    override fun buildStat(input: List<String>): StatLong {
        val regex = "-?\\d+".toRegex()

        val numbers = input
            .flatMap {
                regex.findAll(it)
                    .map { m ->
                        m.groups[0]!!.value.toLong()
                    }
                    .toList()
            }

        val index = numbers
            .groupingBy { it }
            .fold(0) { acc, _ -> acc + 1 }

        val max = index.keys.maxOrNull()

        return StatLong(
            totalCount = numbers.size,
            maxLong = index.keys.maxOrNull() ?: 0L,
            maxCount = index[max] ?: 0
        )
    }
}
