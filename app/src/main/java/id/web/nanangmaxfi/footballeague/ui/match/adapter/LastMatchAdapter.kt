package id.web.nanangmaxfi.footballeague.ui.match.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.utils.DateUtils
import kotlinx.android.synthetic.main.item_match.view.*

class LastMatchAdapter(private val matches: List<MatchResponse>, private val listener: (MatchResponse) -> Unit): RecyclerView.Adapter<LastMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: LastMatchAdapter.ViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(matchResponse: MatchResponse, listener: (MatchResponse) -> Unit){
            itemView.txt_date.text = DateUtils().dateFormat(matchResponse.date+" "+matchResponse.time)
            itemView.txt_time.text = DateUtils().timeFormat(matchResponse.date+" "+matchResponse.time)
            itemView.name_home.text = matchResponse.homeTeam
            itemView.name_away.text = matchResponse.awayTeam
            itemView.score_home.text = matchResponse.homeScore
            itemView.score_away.text = matchResponse.awayScore
            itemView.setOnClickListener {
                listener(matchResponse)
            }
        }
    }
}