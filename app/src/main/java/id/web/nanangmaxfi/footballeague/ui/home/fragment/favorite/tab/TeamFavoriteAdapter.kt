package id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.FavoriteTeam
import kotlinx.android.synthetic.main.item_team.view.*

class TeamFavoriteAdapter (private val favorite: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<TeamFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        )
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit){
            itemView.txt_name.text = favorite.name
            itemView.txt_stadium.text = favorite.stadium
            Glide.with(itemView.context)
                .load(favorite.badge)
                .into(itemView.logo_team)
            itemView.setOnClickListener {
                listener(favorite)
            }
        }
    }
}