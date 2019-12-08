package id.web.nanangmaxfi.footballeague.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.League
import kotlinx.android.synthetic.main.item_league.view.*

class MainAdapter(private val leagues: List<League>, private val listener: (League) -> Unit)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        )
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(leagues: League, listener: (League) -> Unit){
            itemView.txt_title.text = leagues.name
            Glide.with(itemView.context)
                .load(leagues.image)
                .into(itemView.img_badge)

            itemView.setOnClickListener {
                listener(leagues)
            }
        }
    }

}