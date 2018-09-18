package id.go.patikab.rsud.remun.footballapps.adapter.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.go.patikab.rsud.remun.footballapps.R
import id.go.patikab.rsud.remun.footballapps.service.api.response.EventResponse
import kotlinx.android.synthetic.main.item_event_list.view.*

class EventAdapter(private val mEvents: List<EventResponse.Event>,
                   private val mOnClick: (event: EventResponse.Event) -> Unit)
    : RecyclerView.Adapter<EventsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventsViewHolder(inflater.inflate(R.layout.item_event_list, parent, false))
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(mEvents[position], mOnClick)
    }

}

class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(event: EventResponse.Event, onClick: (event: EventResponse.Event) -> Unit) {
        with(itemView) {
            tv_date.text = event.readableDate
            tv_time.text = event.readableTime
            tv_home_name.text = event.homeName
            tv_home_score.text = event.homeScore
            tv_away_name.text = event.awayName
            tv_away_score.text = event.awayScore

            setOnClickListener { onClick(event) }
        }

    }
}
