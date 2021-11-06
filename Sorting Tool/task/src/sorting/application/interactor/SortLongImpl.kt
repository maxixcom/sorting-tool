package sorting.application.interactor

import sorting.domain.gateway.SortGateway
import sorting.domain.usecase.SortLong

class SortLongImpl(
    private val sortGateway: SortGateway
) : SortLong {
    override fun execute(request: SortLong.Request): SortLong.Response {
        val result = runCatching {
            sortGateway.sortLongs(request.input, request.sortBy)
        }
        return SortLong.Response(result)
    }
}
