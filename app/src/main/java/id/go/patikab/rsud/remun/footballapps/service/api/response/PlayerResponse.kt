package id.go.patikab.rsud.remun.footballapps.service.api.response

import com.google.gson.annotations.SerializedName

data class PlayerResponse(@SerializedName("player") val players: List<Player>?,
                          @SerializedName("players") val playersDetail: List<Player>?) {

    data class Player(@SerializedName("idPlayer") val id: String,
                      @SerializedName("strPlayer") val name: String,
                      @SerializedName("strPosition") val position: String,
                      @SerializedName("strDescriptionEN") val overview: String,
                      @SerializedName("strHeight") val height: String,
                      @SerializedName("strWeight") val weight: String,
                      @SerializedName("strCutout") val avatarUrl: String,
                      @SerializedName("strThumb") val bannerUrl: String)

}