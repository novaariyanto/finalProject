package id.go.patikab.rsud.remun.footballapps.service.`interface`
import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse

interface DbInterface {

    fun getFavoriteEvents(): List<EventResponse.Event>
    fun addToFavoriteEvent(event: EventResponse.Event)
    fun removeFromFavoriteEvent(eventId: String)
    fun isFavoriteEventExists(eventId: String): Boolean

    fun getFavoriteTeams(): List<TeamResponse.Team>
    fun addToFavoriteTeam(team: TeamResponse.Team)
    fun removeFromFavoriteTeam(teamId: String)
    fun isFavoriteTeamExists(teamId: String): Boolean
}