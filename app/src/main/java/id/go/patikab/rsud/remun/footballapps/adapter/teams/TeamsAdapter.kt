package id.go.patikab.rsud.remun.footballapps.adapter.teams

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import id.go.patikab.rsud.remun.footballapps.R
import id.go.patikab.rsud.remun.footballapps.service.api.response.TeamResponse
import kotlinx.android.synthetic.main.item_team_list.view.*

class TeamsAdapter(private val mItems: List<TeamResponse.Team>,
                   private val mOnClick: (team: TeamResponse.Team) -> Unit)
    : RecyclerView.Adapter<TeamsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TeamsViewHolder(inflater.inflate(R.layout.item_team_list, parent, false))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(mItems[position], mOnClick)
    }

}

class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(team: TeamResponse.Team, onClick: (team: TeamResponse.Team) -> Unit) {
        with(itemView) {
            Picasso.get().load(team.badgeUrl).into(iv_icon)
            tv_name.text = team.name

            setOnClickListener { onClick(team) }
        }
    }
}
