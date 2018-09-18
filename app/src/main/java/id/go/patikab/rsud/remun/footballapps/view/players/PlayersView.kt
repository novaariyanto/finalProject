package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.PlayerResponse


interface PlayersView {

    fun showPlayers(players: List<PlayerResponse.Player>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}