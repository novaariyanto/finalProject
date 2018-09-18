package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.LeagueResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse


interface TeamsView {

    fun showLeagues(leagues: List<LeagueResponse.League>)

    fun showTeams(teams: List<TeamResponse.Team>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}