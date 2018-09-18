package id.go.patikab.rsud.remun.footballapps.service.lokal

import android.content.Context
import id.go.patikab.rsud.remun.footballapps.service.`interface`.DbInterface
import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import id.go.patikab.rsud.remun.footballapps.service.lokal.table.FavoriteEvent
import id.go.patikab.rsud.remun.footballapps.service.lokal.table.FavoriteTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class AnkoDb(private val mContext: Context) : DbInterface {

    override fun getFavoriteEvents(): List<EventResponse.Event> {
        return mContext.database.use {
            select(FavoriteEvent.TABLE).parseList(classParser<FavoriteEvent>()).map {
                EventResponse.Event(
                        it.eventId ?: "",
                        it.homeName ?: "-",
                        it.awayName ?: "-",
                        it.homeScore ?: "-",
                        it.awayScore ?: "-",
                        "",
                        it.date ?: "-",
                        it.time ?: "-",
                        "", "", "", "",
                        "", "", "", "",
                        "", "", "", "",
                        "", "", "Soccer")
            }
        }
    }

    override fun addToFavoriteEvent(event: EventResponse.Event) {
        mContext.database.use {
            insert(FavoriteEvent.TABLE,
                    FavoriteEvent.EVENT_ID to event.id,
                    FavoriteEvent.HOME_NAME to event.homeName,
                    FavoriteEvent.HOME_SCORE to event.homeScore,
                    FavoriteEvent.AWAY_NAME to event.awayName,
                    FavoriteEvent.AWAY_SCORE to event.awayScore,
                    FavoriteEvent.DATE to event.date,
                    FavoriteEvent.TIME to event.time)
        }
    }

    override fun removeFromFavoriteEvent(eventId: String) {
        mContext.database.use {
            delete(FavoriteEvent.TABLE, "${FavoriteEvent.EVENT_ID} = {eventId}", "eventId" to eventId)
        }
    }

    override fun isFavoriteEventExists(eventId: String): Boolean {
        return mContext.database.use {
            val event = select(FavoriteEvent.TABLE)
                    .whereArgs("${FavoriteEvent.EVENT_ID} = {eventId}", "eventId" to eventId)
                    .parseOpt(classParser<FavoriteEvent>())

            event?.let { true } ?: false
        }
    }

    override fun getFavoriteTeams(): List<TeamResponse.Team> {
        return mContext.database.use {
            select(FavoriteTeam.TABLE).parseList(classParser<FavoriteTeam>()).map {
                TeamResponse.Team(it.teamId ?: "", it.teamName ?: "-", "", "", it.teamBadge
                        ?: "", "")
            }
        }
    }

    override fun addToFavoriteTeam(team: TeamResponse.Team) {
        mContext.database.use {
            insert(FavoriteTeam.TABLE,
                    FavoriteTeam.TEAM_ID to team.id,
                    FavoriteTeam.TEAM_NAME to team.name,
                    FavoriteTeam.TEAM_BADGE to team.badgeUrl)
        }
    }

    override fun removeFromFavoriteTeam(teamId: String) {
        mContext.database.use {
            delete(FavoriteTeam.TABLE, "${FavoriteTeam.TEAM_ID} = {teamId}", "teamId" to teamId)
        }
    }

    override fun isFavoriteTeamExists(teamId: String): Boolean {
        return mContext.database.use {
            val team = select(FavoriteTeam.TABLE)
                    .whereArgs("${FavoriteTeam.TEAM_ID} = {teamId}", "teamId" to teamId)
                    .parseOpt(classParser<FavoriteTeam>())

            team?.let { true } ?: false
        }
    }
}
