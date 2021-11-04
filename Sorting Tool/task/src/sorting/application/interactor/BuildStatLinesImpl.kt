package sorting.application.interactor

import sorting.domain.entity.StatLines
import sorting.domain.gateway.StatGateway
import sorting.domain.usecase.BuildStatLines

class BuildStatLinesImpl(
    private val statGateway: StatGateway<StatLines>
) : BuildStatLines {
    override fun execute(request: BuildStatLines.Request): BuildStatLines.Response {
        val result = runCatching {
            statGateway.buildStat(request.input)
        }
        return BuildStatLines.Response(result)
    }
}
