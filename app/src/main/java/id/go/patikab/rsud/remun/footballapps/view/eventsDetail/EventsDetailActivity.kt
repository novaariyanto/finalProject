package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View

import com.squareup.picasso.Picasso
import id.go.patikab.rsud.remun.footballapps.R
import id.go.patikab.rsud.remun.footballapps.service.api.SportApi
import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import id.go.patikab.rsud.remun.footballapps.service.lokal.AnkoDb
import kotlinx.android.synthetic.main.layout_detail_event.*
import kotlinx.android.synthetic.main.layout_main_evene.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class EventsDetailActivity : AppCompatActivity(), EventsDetailView {

    private val mApi by lazy { SportApi() }
    private val mDb by lazy { AnkoDb(this) }

    private val mPresenter by lazy { EventsDetailPresenter(this, mApi, mDb) }

    private var mAddedToFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_detail_event)

        setSupportActionBar(toolbarevent)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        launch(UI) {
            mPresenter.fetchEventDetail(intent?.getStringExtra("EVENT_ID") ?: "")
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.like, menu)

        val favoriteMenu = menu?.findItem(R.id.action_favorite)
        if (mAddedToFavorite) {
            favoriteMenu?.setIcon(R.drawable.ic_add_to_favorite)
            favoriteMenu?.setOnMenuItemClickListener {
                launch(UI) { mPresenter.removeFromFavorite() }
                toast("Removed from favorite")
                true
            }
        } else {
            favoriteMenu?.setIcon(R.drawable.ic_add_to_favorite)
            favoriteMenu?.setOnMenuItemClickListener {
                launch(UI) { mPresenter.addToFavorite() }
                toast("Added to favorite")
                true
            }
        }

        return true
    }

    override fun showEventDetail(event: EventResponse.Event) {
        tv_date.text = event.readableDate
        tv_time.text = event.readableTime

        tv_home_name.text = event.homeName
        tv_home_score.text = event.homeScore
        tv_home_goals.text = event.homeGoals ?: "-"
        tv_home_goalkeeper.text = event.homeLineupGoalKeeper?.replace("; ", ", ") ?: "-"
        tv_home_defense.text = event.homeLineupDefense?.replace("; ", ", ") ?: "-"
        tv_home_midfield.text = event.homeLineupMidfield?.replace("; ", ", ") ?: "-"
        tv_home_forward.text = event.homeLineupForward?.replace("; ", ", ") ?: "-"
        tv_home_substitute.text = event.homeLineupSubstitutes?.replace("; ", ", ") ?: "-"

        tv_away_name.text = event.awayName
        tv_away_score.text = event.awayScore
        tv_away_goals.text = event.awayGoals ?: "-"
        tv_away_goalkeeper.text = event.awayLineupGoalKeeper?.replace("; ", ", ") ?: "-"
        tv_away_defense.text = event.awayLineupDefense?.replace("; ", ", ") ?: "-"
        tv_away_midfield.text = event.awayLineupMidfield?.replace("; ", ", ") ?: "-"
        tv_away_forward.text = event.awayLineupForward?.replace("; ", ", ") ?: "-"
        tv_away_substitute.text = event.awayLineupSubstitutes?.replace("; ", ", ") ?: "-"

        launch(UI) {
            mPresenter.fetchHomeBadge(event.homeId)
            mPresenter.fetchAwayBadge(event.awayId)
        }
    }

    override fun showHomeBadge(badgeUrl: String) {
        Picasso.get().load(badgeUrl).into(iv_home_badge)
    }

    override fun showAwayBadge(badgeUrl: String) {
        Picasso.get().load(badgeUrl).into(iv_away_badge)
    }

    override fun showMenuAddToFavorite() {
        mAddedToFavorite = false
        invalidateOptionsMenu()
    }

    override fun showMenuAddedToFavorite() {
        mAddedToFavorite = true
        invalidateOptionsMenu()
    }

    override fun showLoading() {
        clp_detail?.show()
        container_detail?.visibility = View.GONE
    }

    override fun hideLoading() {
        clp_detail?.hide()
        container_detail?.visibility = View.VISIBLE
    }

}