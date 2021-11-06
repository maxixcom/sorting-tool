package sorting.application.interactor

import sorting.domain.gateway.SortGateway
import sorting.domain.usecase.SortWords

class SortWordsImpl(
    private val sortGateway: SortGateway
) : SortWords {
    override fun execute(request: SortWords.Request): SortWords.Response {
        val result = runCatching {
            sortGateway.sortWords(request.input, request.sortBy)
        }
        return SortWords.Response(result)
    }
}
