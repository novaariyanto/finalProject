package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.PlayerResponse


interface PlayersDetailView {

    fun showPlayerDetail(player: PlayerResponse.Player)

    fun showLoading()

    fun hideLoading()

}