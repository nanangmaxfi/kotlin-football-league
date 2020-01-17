package id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.tab


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.db.database
import id.web.nanangmaxfi.footballeague.model.FavoriteTeam
import kotlinx.android.synthetic.main.fragment_team_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import id.web.nanangmaxfi.footballeague.ui.team_detail.TeamDetailActivity

class TeamFavoriteFragment : Fragment() {
    private var favorites: MutableList<FavoriteTeam> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
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
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            rv_team.adapter?.notifyDataSetChanged()
        }

        rv_team.layoutManager = LinearLayoutManager(context)
        rv_team.adapter = TeamFavoriteAdapter(favorites){
            val intent = Intent(context, TeamDetailActivity::class.java)
            intent.putExtra(TeamDetailActivity.EXTRA_ID, it.teamId)
            intent.putExtra(TeamDetailActivity.EXTRA_NAME, it.name)
            startActivity(intent)
        }
    }

}
