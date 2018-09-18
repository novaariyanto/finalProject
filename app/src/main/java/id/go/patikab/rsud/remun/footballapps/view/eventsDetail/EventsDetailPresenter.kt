package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.ApiInterface
import id.go.patikab.rsud.remun.footballapps.service.`interface`.DbInterface
import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse


class EventsDetailPresenter(private val mView: EventsDetailView,
                            private val mApi: ApiInterface,
                            private val mDb: DbInterface) {

    private var mEventDetail: EventResponse.Event? = null

    suspend fun fetchEventDetail(eventId: String) {
        mView.showLoading()

        mEventDetail = mApi.detailEvent(eventId).await().events?.get(0)
        mEventDetail?.let { mView.showEventDetail(it) }

        invalidateFavorite(eventId)

        mView.hideLoading()
    }

    suspend fun fetchHomeBadge(teamId: String) {
        val teamDetail = mApi.detailTeam(teamId).await().teams?.get(0)
        teamDetail?.let { mView.showHomeBadge(it.badgeUrl) }
    }

    suspend fun fetchAwayBadge(teamId: String) {
        val teamDetail = mApi.detailTeam(teamId).await().teams?.get(0)
        teamDetail?.let { mView.showAwayBadge(it.badgeUrl) }
    }

    fun addToFavorite() {
        mEventDetail?.let {
            mDb.addToFavoriteEvent(it)
            invalidateFavorite(it.id)
        }
    }

    fun removeFromFavorite() {
        mEventDetail?.let {
            mDb.removeFromFavoriteEvent(it.id)
            invalidateFavorite(it.id)
        }
    }

    private fun invalidateFavorite(eventId: String) {
        val isFavorited = mDb.isFavoriteEventExists(eventId)
        if (isFavorited) {
            mView.showMenuAddedToFavorite()
        } else {
            mView.showMenuAddToFavorite()
        }
    }

}