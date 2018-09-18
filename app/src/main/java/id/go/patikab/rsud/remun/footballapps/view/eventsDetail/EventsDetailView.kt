package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse


interface EventsDetailView {

    fun showEventDetail(event: EventResponse.Event)

    fun showMenuAddToFavorite()

    fun showMenuAddedToFavorite()

    fun showHomeBadge(badgeUrl: String)

    fun showAwayBadge(badgeUrl: String)

    fun showLoading()

    fun hideLoading()

}