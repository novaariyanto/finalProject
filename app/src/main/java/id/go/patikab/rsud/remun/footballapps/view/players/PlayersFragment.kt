package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import  id.go.patikab.rsud.remun.footballapps.service.api.*
import id.go.patikab.rsud.remun.footballapps.*
import id.go.patikab.rsud.remun.footballapps.service.api.response.PlayerResponse
import id.go.patikab.rsud.remun.footballapps.util.openPlayersDetail
import kotlinx.android.synthetic.main.item_list_player.view.*
import kotlinx.android.synthetic.main.layout_base_spiner.*

class PlayersFragment : Fragment(), PlayersView {

    private val mApi by lazy {SportApi() }
    private val mPresenter by lazy { PlayersPresenter(this, mApi) }

    private var mTeamId: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mTeamId?.let {
            launch(UI) { mPresenter.fetchPlayers(it) }
        }
    }

    fun setTeamId(teamId: String) {
        mTeamId = teamId
    }

    override fun showPlayers(players: List<PlayerResponse.Player>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter = PlayersAdapter(players) { player ->
                    activity?.openPlayersDetail(player.id)
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

internal class PlayersAdapter(private val mItems: List<PlayerResponse.Player>,
                              private val mOnClick: (player: PlayerResponse.Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(inflater.inflate(R.layout.item_list_player, parent, false))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(mItems[position], mOnClick)
    }

}

internal class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(player: PlayerResponse.Player, onClick: (player: PlayerResponse.Player) -> Unit) {
        with(itemView) {
            Picasso.get().load(player.avatarUrl).into(iv_icon)
            tv_name.text = player.name
            tv_position.text = player.position

            setOnClickListener { onClick(player) }
        }
    }

}