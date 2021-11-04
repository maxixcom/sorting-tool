package sorting.console.service

import sorting.domain.usecase.SortIntegers

class SortServiceImpl(
    private val useCaseSortIntegers: SortIntegers
) : SortService {
    override fun sortIntegers(lines: List<String>): Result<List<Int>> {
        val request = SortIntegers.Request(lines)
        return useCaseSortIntegers.execute(request).result
    }
}
