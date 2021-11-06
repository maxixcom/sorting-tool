package sorting.application.interactor

import sorting.domain.gateway.SortGateway
import sorting.domain.usecase.SortLines

class SortLinesImpl(
    private val sortGateway: SortGateway
) : SortLines {
    override fun execute(request: SortLines.Request): SortLines.Response {
        val result = runCatching {
            sortGateway.sortLines(request.input, request.sortBy)
        }
        return SortLines.Response(result)
    }
}
