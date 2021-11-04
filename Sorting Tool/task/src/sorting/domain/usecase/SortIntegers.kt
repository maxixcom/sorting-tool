package sorting.domain.usecase

interface SortIntegers {
    fun execute(request: Request): Response
    data class Request(val input: List<String>)
    data class Response(val result: Result<List<Int>>)
}
