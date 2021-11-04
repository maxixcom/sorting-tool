package sorting.console

import sorting.application.gateway.StatLinesGatewayImpl
import sorting.application.gateway.StatLongGatewayImpl
import sorting.application.gateway.StatWordsGatewayImpl
import sorting.application.interactor.BuildStatLinesImpl
import sorting.application.interactor.BuildStatWordsImpl
import sorting.application.interactor.SortIntegersImpl
import sorting.console.service.SortService
import sorting.console.service.SortServiceImpl
import sorting.console.service.StatService
import sorting.console.service.StatServiceImpl
import sorting.domain.entity.StatLines
import sorting.domain.entity.StatLong
import sorting.domain.entity.StatWords
import sorting.domain.gateway.SortIntegersGateway
import sorting.application.gateway.SortIntegersGatewayImpl
import sorting.domain.gateway.StatGateway
import sorting.domain.usecase.BuildStatLines
import sorting.domain.usecase.BuildStatLong
import sorting.domain.usecase.BuildStatLongImpl
import sorting.domain.usecase.BuildStatWords
import sorting.domain.usecase.SortIntegers

object Application {
    private val statLongGateway: StatGateway<StatLong> = StatLongGatewayImpl()
    private val statLinesGateway: StatGateway<StatLines> = StatLinesGatewayImpl()
    private val statWordsGateway: StatGateway<StatWords> = StatWordsGatewayImpl()
    private val sortIntegersGateway: SortIntegersGateway = SortIntegersGatewayImpl()

    private val useCaseBuildStatLong: BuildStatLong = BuildStatLongImpl(
        statGateway = statLongGateway
    )
    private val useCaseBuildStatLines: BuildStatLines = BuildStatLinesImpl(
        statGateway = statLinesGateway
    )
    private val useCaseBuildStatWords: BuildStatWords = BuildStatWordsImpl(
        statGateway = statWordsGateway
    )

    private val useCaseSortIntegers: SortIntegers = SortIntegersImpl(
        gateway = sortIntegersGateway
    )

    val statService: StatService = StatServiceImpl(
        useCaseBuildStatLong = useCaseBuildStatLong,
        useCaseBuildStatLines = useCaseBuildStatLines,
        useCaseBuildStatWords = useCaseBuildStatWords
    )

    val sortService: SortService = SortServiceImpl(
        useCaseSortIntegers = useCaseSortIntegers
    )
}
