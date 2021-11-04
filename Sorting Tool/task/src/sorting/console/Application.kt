package sorting.console

import sorting.application.gateway.StatLongGatewayImpl
import sorting.application.interactor.BuildStatLinesImpl
import sorting.application.interactor.BuildStatWordsImpl
import sorting.console.service.StatService
import sorting.console.service.StatServiceImpl
import sorting.domain.entity.StatLines
import sorting.domain.entity.StatLong
import sorting.domain.entity.StatWords
import sorting.domain.gateway.StatGateway
import sorting.application.gateway.StatLinesGatewayImpl
import sorting.application.gateway.StatWordsGatewayImpl
import sorting.domain.usecase.BuildStatLines
import sorting.domain.usecase.BuildStatLong
import sorting.domain.usecase.BuildStatLongImpl
import sorting.domain.usecase.BuildStatWords

object Application {
    private val statLongGateway: StatGateway<StatLong> = StatLongGatewayImpl()
    private val statLinesGateway: StatGateway<StatLines> = StatLinesGatewayImpl()
    private val statWordsGateway: StatGateway<StatWords> = StatWordsGatewayImpl()
    private val useCaseBuildStatLong: BuildStatLong = BuildStatLongImpl(
        statGateway = statLongGateway
    )
    private val useCaseBuildStatLines: BuildStatLines = BuildStatLinesImpl(
        statGateway = statLinesGateway
    )
    private val useCaseBuildStatWords: BuildStatWords = BuildStatWordsImpl(
        statGateway = statWordsGateway
    )
    val statService: StatService = StatServiceImpl(
        useCaseBuildStatLong = useCaseBuildStatLong,
        useCaseBuildStatLines = useCaseBuildStatLines,
        useCaseBuildStatWords = useCaseBuildStatWords
    )
}
