package id.go.patikab.rsud.remun.footballapps.service.api

import android.accounts.NetworkErrorException
import com.androidnetworking.AndroidNetworking
import id.go.patikab.rsud.remun.footballapps.service.`interface`.ApiInterface
import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.LeagueResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.PlayerResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.coroutines.experimental.bg

class SportApi: ApiInterface {

    override fun leagues(type: String): Deferred<LeagueResponse> = req(
            ApiPoint.LEAGUES,
            LeagueResponse::class.java,
            "s" to type
    ) as Deferred<LeagueResponse>

    override fun pastEvents(leagueId: String): Deferred<EventResponse> = req(
            ApiPoint.EVENTS_PAST,
            EventResponse::class.java,
            "id" to leagueId
    ) as Deferred<EventResponse>

    override fun nextEvents(leagueId: String): Deferred<EventResponse> = req(
            ApiPoint.EVENTS_NEXT,
            EventResponse::class.java,
            "id" to leagueId
    ) as Deferred<EventResponse>

    override fun searchEvents(keywords: String): Deferred<EventResponse> {
        return req(
                ApiPoint.EVENTS_SEARCH,
                EventResponse::class.java,
                "e" to keywords
        ) as Deferred<EventResponse>
    }

    override fun detailEvent(eventId: String): Deferred<EventResponse> = req(
            ApiPoint.EVENTS_DETAIL,
            EventResponse::class.java,
            "id" to eventId
    ) as Deferred<EventResponse>

    override fun teams(leagueName: String): Deferred<TeamResponse> = req(
            ApiPoint.TEAMS,
            TeamResponse::class.java,
            "l" to leagueName
    ) as Deferred<TeamResponse>

    override fun searchTeams(keywords: String): Deferred<TeamResponse> = req(
            ApiPoint.TEAMS_SEARCH,
            TeamResponse::class.java,
            "t" to keywords
    ) as Deferred<TeamResponse>

    override fun detailTeam(teamId: String): Deferred<TeamResponse> = req(
            ApiPoint.TEAMS_DETAIL,
            TeamResponse::class.java,
            "id" to teamId
    ) as Deferred<TeamResponse>

    override fun players(teamId: String): Deferred<PlayerResponse> = req(
            ApiPoint.PLAYERS,
            PlayerResponse::class.java,
            "id" to teamId
    ) as Deferred<PlayerResponse>

    override fun detailPlayer(playerId: String): Deferred<PlayerResponse> = req(
            ApiPoint.PLAYERS_DETAIL,
            PlayerResponse::class.java,
            "id" to playerId
    ) as Deferred<PlayerResponse>

    private fun req(url: String, type: Class<*>, vararg pairs: Pair<String, String>): Deferred<Any> {
        return bg {
            val response = AndroidNetworking
                    .get(url)
                    .apply { pairs.forEach { addQueryParameter(it.first, it.second) } }
                    .build()
                    .executeForObject(type)

            if (!response.isSuccess) {
                throw NetworkErrorException(response.error.localizedMessage)
            }

            response.result
        }
    }

}