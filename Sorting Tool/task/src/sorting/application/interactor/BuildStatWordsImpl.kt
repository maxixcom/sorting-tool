package sorting.application.interactor

import sorting.domain.entity.StatWords
import sorting.domain.gateway.StatGateway
import sorting.domain.usecase.BuildStatWords

class BuildStatWordsImpl(
    private val statGateway: StatGateway<StatWords>
) : BuildStatWords {
    override fun execute(request: BuildStatWords.Request): BuildStatWords.Response {
        val result = runCatching {
            statGateway.buildStat(request.input)
        }
        return BuildStatWords.Response(result)
    }
}
