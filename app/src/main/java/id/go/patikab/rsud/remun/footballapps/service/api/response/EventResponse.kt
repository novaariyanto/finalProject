package id.go.patikab.rsud.remun.footballapps.service.api.response

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class EventResponse(@SerializedName("events") val events: List<Event>?,
                         @SerializedName("event") val eventsSearch: List<Event>?) {

    data class Event(@SerializedName("idEvent") val id: String,
                     @SerializedName("strHomeTeam") val homeName: String,
                     @SerializedName("strAwayTeam") val awayName: String,
                     @SerializedName("intHomeScore") val homeScore: String,
                     @SerializedName("intAwayScore") val awayScore: String,
                     @SerializedName("dateEvent") val dateEvent: String,
                     @SerializedName("strDate") val date: String?,
                     @SerializedName("strTime") val time: String?,

                     @SerializedName("idHomeTeam") val homeId: String,
                     @SerializedName("idAwayTeam") val awayId: String,
                     @SerializedName("strHomeGoalDetails") val homeGoals: String?,
                     @SerializedName("strAwayGoalDetails") val awayGoals: String?,
                     @SerializedName("strHomeLineupGoalkeeper") val homeLineupGoalKeeper: String?,
                     @SerializedName("strAwayLineupGoalkeeper") val awayLineupGoalKeeper: String?,
                     @SerializedName("strHomeLineupDefense") val homeLineupDefense: String?,
                     @SerializedName("strAwayLineupDefense") val awayLineupDefense: String?,
                     @SerializedName("strHomeLineupMidfield") val homeLineupMidfield: String?,
                     @SerializedName("strAwayLineupMidfield") val awayLineupMidfield: String?,
                     @SerializedName("strHomeLineupForward") val homeLineupForward: String?,
                     @SerializedName("strAwayLineupForward") val awayLineupForward: String?,
                     @SerializedName("strHomeLineupSubstitutes") val homeLineupSubstitutes: String?,
                     @SerializedName("strAwayLineupSubstitutes") val awayLineupSubstitutes: String?,

                     @SerializedName("strSport") val sport: String
    ) {

        val readableDate: String
        get() {
            if (date == null) return "-"

            return try {
                val sdf = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                val obj = sdf.parse(date)

                sdf.applyPattern("E, dd MMM yyyy")

                sdf.format(obj)
            } catch (e: ParseException) {
                "-"
            }
        }

        val readableTime: String
        get() {
            return time?.split("+")?.get(0) ?: "-"
        }

    }

}