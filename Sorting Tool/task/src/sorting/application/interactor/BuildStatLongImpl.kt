package sorting.domain.usecase

import sorting.domain.entity.StatLong
import sorting.domain.gateway.StatGateway

class BuildStatLongImpl(
    private val statGateway: StatGateway<StatLong>
) : BuildStatLong {
    override fun execute(request: BuildStatLong.Request): BuildStatLong.Response {
        val result = runCatching {
            statGateway.buildStat(request.input)
        }
        return BuildStatLong.Response(result)
    }
}
