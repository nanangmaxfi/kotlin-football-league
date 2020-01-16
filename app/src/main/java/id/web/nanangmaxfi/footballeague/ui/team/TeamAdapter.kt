package id.web.nanangmaxfi.footballeague.ui.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
import id.web.nanangmaxfi.footballeague.model.TeamResponse
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val teams: ListTeamResponse?, private val listener: (TeamResponse) -> Unit): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        )
    }

    override fun getItemCount(): Int {
        if (teams?.teamResponses.isNullOrEmpty()) {
            return 0
        } else {
            return teams!!.teamResponses.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams!!.teamResponses[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(teamResponse: TeamResponse, listener: (TeamResponse) -> Unit){
            itemView.txt_name.text = teamResponse.name
            itemView.txt_stadium.text = teamResponse.stadium
            Glide.with(itemView.context)
                .load(teamResponse.badge)
                .into(itemView.logo_team)
            itemView.setOnClickListener {
                listener(teamResponse)
            }
        }
    }
}