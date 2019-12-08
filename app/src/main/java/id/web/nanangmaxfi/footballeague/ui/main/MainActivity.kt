package id.web.nanangmaxfi.footballeague.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import id.web.nanangmaxfi.footballeague.ui.detail.DetailActivity
import id.web.nanangmaxfi.footballeague.model.League
import id.web.nanangmaxfi.footballeague.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {
    private var items: MutableList<League> = mutableListOf()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.title = resources.getString(R.string.app_name)

        init()

        adapter = MainAdapter(items){
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, it.id)
            intent.putExtra(DetailActivity.EXTRA_NAME, it.name)
            startActivity(intent)
        }

        rv_league.layoutManager = GridLayoutManager(this,2)
        rv_league.adapter = adapter
    }

    private fun init(){
        val id = resources.getStringArray(R.array.id)
        val name = resources.getStringArray(R.array.name)
        val image = resources.obtainTypedArray(R.array.image)

        items.clear()

        for (i in name.indices){
            items.add(League(id[i],name[i], image.getResourceId(i, 0)))
        }

        image.recycle()
    }

}
