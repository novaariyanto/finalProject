package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.DbInterface


class FavoriteEventsPresenter(private val mView: FavoriteEventsView,
                              private val mDb: DbInterface) {

    suspend fun fetchFavoriteEvents() {
        mView.showLoading()

        val events = mDb.getFavoriteEvents()
        if (events.isNotEmpty()) {
            mView.showEvents(events)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}