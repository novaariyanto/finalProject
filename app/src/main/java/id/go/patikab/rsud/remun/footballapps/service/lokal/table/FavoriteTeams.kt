package id.go.patikab.rsud.remun.footballapps.service.lokal.table

data class FavoriteTeam(val id: Long?,
                        val teamId: String?,
                        val teamName: String?,
                        val teamBadge: String?) {

    companion object {
        const val TABLE = "tb_team_favorite"
        const val ID = "_ID"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_BADGE = "TEAM_BADGE"
    }

}