package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.DbInterface


class FavoriteTeamsPresenter(private val mView: FavoriteTeamsView,
                             private val mDb: DbInterface) {

    suspend fun fetchFavoriteTeams() {
        mView.showLoading()

        val teams = mDb.getFavoriteTeams()
        if (teams.isNotEmpty()) {
            mView.showFavoriteTeams(teams)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}