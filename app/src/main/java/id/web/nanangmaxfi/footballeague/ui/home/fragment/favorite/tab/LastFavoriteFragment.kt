package id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.tab


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.db.database
import id.web.nanangmaxfi.footballeague.model.Favorite
import id.web.nanangmaxfi.footballeague.ui.detail_match.DetailMatchActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

import kotlinx.android.synthetic.main.fragment_last_favorite.*
import org.jetbrains.anko.support.v4.onRefresh

import id.web.nanangmaxfi.footballeague.utils.MatchUtils

class LastFavoriteFragment : Fragment() {
    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipe_refresh.onRefresh {
            showFavorite()
        }

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipe_refresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite.filter { it.matchType.equals(MatchUtils.LAST_MATCH) })
            rv_last_match.adapter?.notifyDataSetChanged()
        }

        rv_last_match.layoutManager = LinearLayoutManager(context)
        rv_last_match.adapter = LastFavoriteAdapter(favorites){
            val intent = Intent(context,DetailMatchActivity::class.java)
            intent.putExtra(DetailMatchActivity.EXTRA_ID, it.matchId)
            intent.putExtra(DetailMatchActivity.EXTRA_NAME, "Match")
            startActivity(intent)
        }
    }


}
