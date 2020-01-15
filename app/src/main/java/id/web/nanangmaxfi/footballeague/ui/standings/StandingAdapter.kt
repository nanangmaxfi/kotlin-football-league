package id.web.nanangmaxfi.footballeague.ui.standings

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.ListStandingResponse
import id.web.nanangmaxfi.footballeague.model.StandingResponse
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter(private val standing: ListStandingResponse?): RecyclerView.Adapter<StandingAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false)
        )
    }

    override fun getItemCount(): Int {
        if (standing?.standingResponses.isNullOrEmpty()) {
            return 0
        } else {
            return standing!!.standingResponses.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(standing!!.standingResponses[position], position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(standingResponse: StandingResponse, position: Int){
            itemView.txt_position.text = (position+1).toString()
            itemView.txt_team.text = standingResponse.name
            itemView.txt_played.text = standingResponse.played
            itemView.txt_win.text = standingResponse.win
            itemView.txt_draw.text = standingResponse.draw
            itemView.txt_gfga.text = TextUtils.concat(standingResponse.gf,"-",standingResponse.ga)
            itemView.txt_pts.text = standingResponse.pts
        }
    }
}