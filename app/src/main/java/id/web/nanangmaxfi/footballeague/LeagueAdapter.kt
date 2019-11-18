package id.web.nanangmaxfi.footballeague

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class LeagueAdapter(private val context: Context, private val leagues: List<League>, private val listener: (League) -> Unit)
    : RecyclerView.Adapter<LeagueAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LeagueItemUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgItem: ImageView = view.findViewById(LeagueItemUI.imgLeague)
        private val txtName: TextView = view.findViewById(LeagueItemUI.name)

        fun bindItem(leagues: League, listener: (League) -> Unit){
            txtName.text = leagues.name
            Glide.with(itemView.context)
                .load(leagues.image)
                .into(imgItem)

            itemView.setOnClickListener {
                listener(leagues)
            }
        }
    }

    class LeagueItemUI : AnkoComponent<ViewGroup>{
        companion object{
            val imgLeague = 1
            val name = 2
        }
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        id = imgLeague
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams {
                        height = dip(100)
                        width = dip(100)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    textView {
                        id = name
                        textSize = 16f

                    }.lparams {
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                        gravity = Gravity.CENTER
                    }
                }
        }

    }
}