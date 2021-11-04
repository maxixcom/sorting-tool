package sorting.application.interactor

import sorting.domain.gateway.SortIntegersGateway
import sorting.domain.usecase.SortIntegers

class SortIntegersImpl(
    private val gateway: SortIntegersGateway
) : SortIntegers {
    override fun execute(request: SortIntegers.Request): SortIntegers.Response {
        val result = runCatching {
            gateway.sort(request.input)
        }
        return SortIntegers.Response(result)
    }
}
