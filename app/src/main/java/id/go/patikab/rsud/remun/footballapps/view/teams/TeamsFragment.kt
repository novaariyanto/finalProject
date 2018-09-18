package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import id.go.patikab.rsud.remun.footballapps.service.api.*
import id.go.patikab.rsud.remun.footballapps.R
import kotlinx.android.synthetic.main.layout_base_spiner.*
import kotlinx.android.synthetic.main.layout_main_team.*
import id.go.patikab.rsud.remun.footballapps.adapter.teams.*
import id.go.patikab.rsud.remun.footballapps.service.api.response.LeagueResponse
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import id.go.patikab.rsud.remun.footballapps.util.openTeamsDetail
import id.go.patikab.rsud.remun.footballapps.util.openTeamsSearch

class TeamsFragment : Fragment(), TeamsView {

    private val mApi by lazy { SportApi() }
    private val mPresenter by lazy { TeamsPresenter(this, mApi) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_main_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchLeagues() }

        with(toolbar) {
            inflateMenu(R.menu.cari)

            val searchMenu = menu.findItem(R.id.action_cari)
            searchMenu.actionView = null
            searchMenu.setOnMenuItemClickListener {
                activity?.openTeamsSearch()
                true
            }
        }
    }

    override fun showLeagues(leagues: List<LeagueResponse.League>) {
        sp_leagues?.let {
            with(sp_leagues) {
                adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, leagues.map { it.name })
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        launch(UI) { mPresenter.fetchTeams(leagues[position].name) }
                    }
                }
            }
        }
    }

    override fun showTeams(teams: List<TeamResponse.Team>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter = TeamsAdapter(teams) { team ->
                    activity?.openTeamsDetail(team.id)
                }
            }
        }
    }

    override fun showLoading() {
        rv_data?.visibility = View.GONE
        clp_data?.visibility = View.VISIBLE
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.visibility = View.GONE
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.visibility = View.GONE
        ph_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        ph_data?.visibility = View.GONE
    }

}