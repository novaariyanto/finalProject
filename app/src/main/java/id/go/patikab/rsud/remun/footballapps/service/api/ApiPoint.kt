package id.go.patikab.rsud.remun.footballapps.service.api

object ApiPoint {
    private const val HOST = "https://www.thesportsdb.com"
    //    host

    const val LEAGUES = "$HOST/api/v1/json/1/search_all_leagues.php"
    // ?s=soccer
    const val EVENTS_PAST = "$HOST/api/v1/json/1/eventspastleague.php"
    // ?id=LEAGUE_ID
    const val EVENTS_NEXT = "$HOST/api/v1/json/1/eventsnextleague.php"
    // ?id=LEAGUE_ID
    const val EVENTS_SEARCH = "$HOST/api/v1/json/1/searchevents.php"
    // ?e=KEYWORDS
    const val EVENTS_DETAIL = "$HOST/api/v1/json/1/lookupevent.php"
    // ?id=EVENT_ID

    const val TEAMS = "$HOST/api/v1/json/1/search_all_teams.php"
    // ?l=LEAGUE_NAME
    const val TEAMS_SEARCH = "$HOST/api/v1/json/1/searchteams.php"
    // ?t=KEYWORDS
    const val TEAMS_DETAIL = "$HOST/api/v1/json/1/lookupteam.php"
    // ?id=TEAM_ID

    const val PLAYERS = "$HOST/api/v1/json/1/lookup_all_players.php"
    // ?id=TEAM_ID
    const val PLAYERS_DETAIL = "$HOST/api/v1/json/1/lookupplayer.php"
    // ?id=PLAYER_ID

}