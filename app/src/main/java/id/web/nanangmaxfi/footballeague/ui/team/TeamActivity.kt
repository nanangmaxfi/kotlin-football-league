package id.web.nanangmaxfi.footballeague.ui.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
import id.web.nanangmaxfi.footballeague.model.TeamResponse
import id.web.nanangmaxfi.footballeague.repository.TeamRepository
import id.web.nanangmaxfi.footballeague.ui.team_detail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class TeamActivity : AppCompatActivity(), TeamView {
    private lateinit var presenter: TeamPresenter

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F
        actionBar?.title = "Team"

        presenter = TeamPresenter(this, TeamRepository())
        val idLeague = intent.getStringExtra(EXTRA_ID)

        presenter.getTeam(idLeague)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        rv_team.visibility = View.GONE
        txt_not_found.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        rv_team.visibility = View.VISIBLE
        txt_not_found.visibility = View.GONE
    }

    override fun onDataLoaded(data: ListTeamResponse?) {
        rv_team.layoutManager = LinearLayoutManager(this)
        rv_team.adapter = TeamAdapter(data){
            val intent = Intent(this,TeamDetailActivity::class.java)
            intent.putExtra(TeamDetailActivity.EXTRA_ID, it.id)
            intent.putExtra(TeamDetailActivity.EXTRA_NAME, it.name)
            startActivity(intent)
        }
    }

    override fun onDataError() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchQuery(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun searchQuery(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    return false
                }
                presenter.getSearch(newText)
                return true
            }

        })
    }

    override fun notFound() {
        progress_bar.visibility = View.GONE
        rv_team.visibility = View.GONE
        txt_not_found.visibility = View.VISIBLE
    }

    override fun dataSearch(dataSearch: ListTeamResponse?) {
        val list: MutableList<TeamResponse> = ArrayList()
        for (item in dataSearch!!.teamResponses){
            if (item.sport == "Soccer"){
                list.add(item)
            }

        }

        if (list.isEmpty()){
            notFound()
        }
        else{
            rv_team.layoutManager = LinearLayoutManager(this)
            rv_team.adapter = TeamSearchAdapter(list){
                val intent = Intent(this,TeamDetailActivity::class.java)
                intent.putExtra(TeamDetailActivity.EXTRA_ID, it.id)
                intent.putExtra(TeamDetailActivity.EXTRA_NAME, it.name)
                startActivity(intent)

                hideLoading()
            }


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
