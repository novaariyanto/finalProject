package id.go.patikab.rsud.remun.footballapps.service.lokal.table


data class FavoriteEvent(val id: Long?,
                         val eventId: String?,
                         val homeName: String?,
                         val homeScore: String?,
                         val awayName: String?,
                         val awayScore: String?,
                         val date: String?,
                         val time: String?) {

    companion object {
        const val TABLE = "tb_favorite_event"
        const val ID = "ID_"
        const val EVENT_ID = "EVENT_ID"
        const val HOME_NAME = "HOME_NAME"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_NAME = "AWAY_NAME"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val DATE = "EVENT_DATE"
        const val TIME = "EVENT_TIME"
    }

}