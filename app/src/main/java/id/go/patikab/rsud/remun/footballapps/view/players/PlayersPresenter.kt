package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.ApiInterface


class PlayersPresenter(private val mView: PlayersView,
                       private val mApi: ApiInterface) {

    suspend fun fetchPlayers(teamId: String) {
        mView.showLoading()

        val players = mApi.players(teamId).await().players
        if (players != null && players.isNotEmpty()) {
            mView.showPlayers(players)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}