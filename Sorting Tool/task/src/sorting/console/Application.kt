package sorting.console

import sorting.application.gateway.SortGatewayImpl
import sorting.application.interactor.SortLinesImpl
import sorting.application.interactor.SortLongImpl
import sorting.application.interactor.SortWordsImpl
import sorting.console.service.SortService
import sorting.console.service.SortServiceImpl
import sorting.domain.gateway.SortGateway
import sorting.domain.usecase.SortLines
import sorting.domain.usecase.SortLong
import sorting.domain.usecase.SortWords

object Application {
    private val sortGateway: SortGateway = SortGatewayImpl()

    private val useCaseSortLong: SortLong = SortLongImpl(sortGateway)
    private val useCaseSortWords: SortWords = SortWordsImpl(sortGateway)
    private val useCaseSortLines: SortLines = SortLinesImpl(sortGateway)

    val sortService: SortService = SortServiceImpl(
        useCaseSortLong = useCaseSortLong,
        useCaseSortWords = useCaseSortWords,
        useCaseSortLines = useCaseSortLines,
    )
}
