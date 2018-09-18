package id.go.patikab.rsud.remun.footballapps

import id.go.patikab.rsud.remun.footballapps.service.`interface`.ApiInterface


class EventsNextPresenter(private val mView: EventsNextView,
                          private val mApi: ApiInterface) {

    suspend fun fetchLeagues() {
        mView.showLoading()

        val leagues = mApi.leagues().await().leagues
        if (leagues != null && leagues.isNotEmpty()) {
            mView.showLeagues(leagues)
            mView.hideLoading()

            fetchEvents(leagues[0].id)
        } else {
            mView.showPlaceholder()
        }
    }

    suspend fun fetchEvents(leagueId: String) {
        mView.showLoading()

        val events = mApi.nextEvents(leagueId).await().events
        if (events != null && events.isNotEmpty()) {
            mView.showEvents(events)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}