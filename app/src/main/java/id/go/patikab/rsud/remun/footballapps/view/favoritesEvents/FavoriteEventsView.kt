package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse


interface FavoriteEventsView {

    fun showEvents(events: List<EventResponse.Event>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}