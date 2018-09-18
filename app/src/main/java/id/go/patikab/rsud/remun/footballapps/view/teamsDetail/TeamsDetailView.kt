package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse


interface TeamsDetailView {

    fun showTeamDetail(team: TeamResponse.Team)

    fun showMenuAddToFavorite()

    fun showMenuAddedToFavorite()

    fun showLoading()

    fun hideLoading()

}