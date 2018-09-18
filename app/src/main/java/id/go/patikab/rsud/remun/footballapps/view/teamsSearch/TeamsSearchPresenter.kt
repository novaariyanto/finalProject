package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.ApiInterface
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import kotlinx.coroutines.experimental.Deferred

class TeamsSearchPresenter(private val mView: TeamsSearchView,
                           private val mApi: ApiInterface) {

    private var mDeferredSearch: Deferred<TeamResponse>? = null

    suspend fun fetchSearchTeams(keywords: String) {
        if (keywords.isBlank()) return

        mView.showLoading()

        mDeferredSearch?.cancel(IllegalStateException())
        mDeferredSearch = mApi.searchTeams(keywords)

        try {
            val teams = mDeferredSearch?.await()?.teams

            if (teams != null && teams.isNotEmpty()) {
                mView.showTeams(teams)
                mView.hideLoading()
            } else {
                mView.showPlaceholder()
            }
        } catch (e: IllegalStateException) { }
    }

}