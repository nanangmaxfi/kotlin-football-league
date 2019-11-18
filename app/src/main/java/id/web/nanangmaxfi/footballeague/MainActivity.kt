package id.web.nanangmaxfi.footballeague

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        verticalLayout {
            recyclerView {
                layoutManager = GridLayoutManager(context,2)
                adapter = LeagueAdapter(context, items){
                    startActivity<DetailActivity>("league" to it)
                }
            }
        }

    }

    private fun init(){
        val name = resources.getStringArray(R.array.name)
        val image = resources.obtainTypedArray(R.array.image)
        val description = resources.getStringArray(R.array.description)
        items.clear()

        for (i in name.indices){
            items.add(League(name[i], image.getResourceId(i,0), description[i]))
        }

        image.recycle()
    }

}
