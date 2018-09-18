package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.ApiInterface


class PlayersDetailPresenter(private val mView: PlayersDetailView,
                             private val mApi: ApiInterface) {

    suspend fun fetchPlayerDetail(playerId: String) {
        mView.showLoading()

        val player = mApi.detailPlayer(playerId).await().playersDetail?.get(0)
        player?.let { mView.showPlayerDetail(it) }

        mView.hideLoading()
    }

}