package id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.Favorite
import id.web.nanangmaxfi.footballeague.utils.DateUtils
import kotlinx.android.synthetic.main.item_match.view.*

class NextFavoriteAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<NextFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        )
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit){
            itemView.txt_date.text = DateUtils().dateFormat(favorite.date+" "+favorite.time)
            itemView.txt_time.text = DateUtils().timeFormat(favorite.date+" "+favorite.time)
            itemView.name_home.text = favorite.homeTeam
            itemView.name_away.text = favorite.awayTeam
            itemView.score_home.text = "-"
            itemView.score_away.text = "-"
            itemView.setOnClickListener {
                listener(favorite)
            }
        }
    }
}