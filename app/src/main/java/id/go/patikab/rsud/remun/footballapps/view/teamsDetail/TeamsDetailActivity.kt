package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.squareup.picasso.Picasso
import id.go.patikab.rsud.remun.footballapps.service.api.SportApi
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import id.go.patikab.rsud.remun.footballapps.service.lokal.AnkoDb
import kotlinx.android.synthetic.main.layout_detail_team.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class TeamsDetailActivity : AppCompatActivity(), TeamsDetailView {

    private val mApi by lazy { SportApi() }
    private val mDb by lazy { AnkoDb(applicationContext) }

    private val mPresenter by lazy { TeamsDetailPresenter(this, mApi, mDb) }

    private var mAddedToFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_detail_team)

        setSupportActionBar(toolbardetailteam)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        launch(UI) {
            mPresenter.fetchTeamDetail(intent?.getStringExtra("TEAM_ID") ?: "")
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

    override fun showTeamDetail(team: TeamResponse.Team) {
        Picasso.get().load(team.badgeUrl).into(iv_icon)
        tv_name.text = team.name
        tv_year.text = team.formedYear
        tv_desc.text = team.stadium

        vp_team_detail.adapter = TeamPagerAdapter(team.overview ?: "No overview", team.id, supportFragmentManager)
        tab_team_detail.setupWithViewPager(vp_team_detail)
    }

    override fun showMenuAddToFavorite() {
        mAddedToFavorite = false
        invalidateOptionsMenu()
    }

    override fun showMenuAddedToFavorite() {
        mAddedToFavorite = true
        invalidateOptionsMenu()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

}

internal class TeamPagerAdapter(private val mOverview: String,
                                private val mTeamId: String,
                                fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    private val mPages by lazy {
        listOf(
                TeamsDetailOverviewFragment().apply { setOverview(mOverview) },
                PlayersFragment().apply { setTeamId(mTeamId) })
    }

    private val mTitles by lazy { listOf("Overview", "Players") }

    override fun getItem(position: Int): Fragment {
        return mPages[position]
    }

    override fun getCount(): Int {
        return mPages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}