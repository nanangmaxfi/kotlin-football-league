package id.web.nanangmaxfi.footballeague.ui.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
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
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        rv_team.visibility = View.VISIBLE
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
