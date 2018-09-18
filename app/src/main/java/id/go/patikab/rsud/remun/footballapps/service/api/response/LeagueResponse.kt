package id.go.patikab.rsud.remun.footballapps.service.api.response

import com.google.gson.annotations.SerializedName

data class LeagueResponse(@SerializedName("countrys") val leagues: List<League>?) {

    data class League(@SerializedName("idLeague") val id: String,
                      @SerializedName("strLeague") val name: String)

}