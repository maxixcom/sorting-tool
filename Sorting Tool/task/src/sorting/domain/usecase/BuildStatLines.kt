package sorting.domain.usecase

import sorting.domain.entity.StatLines

interface BuildStatLines {
    fun execute(request: Request): Response
    data class Request(val input: List<String>)
    data class Response(val result: Result<StatLines>)
}
