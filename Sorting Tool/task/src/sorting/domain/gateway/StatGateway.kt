package sorting.domain.gateway

interface StatGateway<T> {
    fun buildStat(input: List<String>): T
}
