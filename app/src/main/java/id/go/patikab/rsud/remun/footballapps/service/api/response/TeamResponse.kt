package id.go.patikab.rsud.remun.footballapps.service.api.response

import com.google.gson.annotations.SerializedName

data class TeamResponse(@SerializedName("teams") val teams: List<Team>?) {

    data class Team(@SerializedName("idTeam") val id: String,
                    @SerializedName("strTeam") val name: String,
                    @SerializedName("intFormedYear") val formedYear: String,
                    @SerializedName("strStadium") val stadium: String,
                    @SerializedName("strTeamBadge") val badgeUrl: String,
                    @SerializedName("strDescriptionEN") val overview: String?)

}