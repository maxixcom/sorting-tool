package sorting.domain.usecase

import sorting.domain.entity.StatWords

interface BuildStatWords {
    fun execute(request: Request): Response
    data class Request(val input: List<String>)
    data class Response(val result: Result<StatWords>)
}
