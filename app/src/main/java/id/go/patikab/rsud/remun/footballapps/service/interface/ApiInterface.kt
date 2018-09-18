package id.go.patikab.rsud.remun.footballapps.service.`interface`

import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.LeagueResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.PlayerResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import kotlinx.coroutines.experimental.Deferred

interface ApiInterface{

    fun leagues(type: String = "soccer"): Deferred<LeagueResponse>

    fun pastEvents(leagueId: String): Deferred<EventResponse>
    fun nextEvents(leagueId: String): Deferred<EventResponse>
    fun searchEvents(keywords: String): Deferred<EventResponse>
    fun detailEvent(eventId: String): Deferred<EventResponse>

    fun teams(leagueName: String): Deferred<TeamResponse>
    fun searchTeams(keywords: String): Deferred<TeamResponse>
    fun detailTeam(teamId: String): Deferred<TeamResponse>

    fun players(teamId: String): Deferred<PlayerResponse>
    fun detailPlayer(playerId: String): Deferred<PlayerResponse>
}