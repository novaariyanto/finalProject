package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import id.go.patikab.rsud.remun.footballapps.service.api.SportApi
import kotlinx.android.synthetic.main.layout_base_spiner.*
import kotlinx.android.synthetic.main.layout_main_evene.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import  id.go.patikab.rsud.remun.footballapps.adapter.teams.*
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import id.go.patikab.rsud.remun.footballapps.util.openTeamsDetail

class TeamsSearchActivity : AppCompatActivity(), TeamsSearchView {

    private val mApi by lazy { SportApi() }
    private val mPresenter by lazy { TeamsSearchPresenter(this, mApi) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_search)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        hideLoading()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cari, menu)

        val menuSearch = menu?.findItem(R.id.action_cari)
        val searchView = menuSearch?.actionView as SearchView?
        searchView?.let {
            with(it) {
                isIconified = false
                setOnCloseListener {
                    searchView.setQuery("", false)
                    true
                }
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean = false
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return newText?.let {
                            launch(UI) { mPresenter.fetchSearchTeams(it) }
                            true
                        } ?: false
                    }
                })
            }
        }

        return true
    }

    override fun showTeams(teams: List<TeamResponse.Team>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter =TeamsAdapter(teams) { team ->
                    openTeamsDetail(team.id)
                }
            }
        }
    }

    override fun showLoading() {
        rv_data?.visibility = View.GONE
        clp_data?.show()
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.hide()
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.hide()
        ph_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        ph_data?.visibility = View.GONE
    }

}