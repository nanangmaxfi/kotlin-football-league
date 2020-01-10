package id.web.nanangmaxfi.footballeague.ui.home.fragment.league


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.League
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.fragment_league.*

import id.web.nanangmaxfi.footballeague.ui.detail.DetailActivity


class LeagueFragment : Fragment() {
    private var items: MutableList<League> = mutableListOf()
    private lateinit var adapter: LeagueAdapter

    companion object{
        fun newInstance(): LeagueFragment {
            val fragment = LeagueFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.app_name)
        }

        init()

        adapter = LeagueAdapter(items) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, it.id)
            intent.putExtra(DetailActivity.EXTRA_NAME, it.name)
            startActivity(intent)
        }

        rv_league.layoutManager = GridLayoutManager(context,2)
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
