package sorting.application.gateway

import sorting.domain.gateway.SortIntegersGateway

class SortIntegersGatewayImpl : SortIntegersGateway {
    override fun sort(input: List<String>): List<Int> {
        val regex = "-?\\d+".toRegex()

        val numbers = input
            .flatMap {
                regex.findAll(it)
                    .map { m ->
                        m.groups[0]!!.value.toInt()
                    }
                    .toList()
            }
        return numbers.sorted()
    }
}
