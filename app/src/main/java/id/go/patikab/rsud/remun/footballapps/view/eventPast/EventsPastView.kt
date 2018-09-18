package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.LeagueResponse


interface EventsPastView {

    fun showEvents(events: List<EventResponse.Event>)

    fun showLeagues(leagues: List<LeagueResponse.League>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}