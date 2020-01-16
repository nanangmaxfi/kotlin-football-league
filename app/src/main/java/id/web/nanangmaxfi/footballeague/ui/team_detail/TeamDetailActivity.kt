package id.web.nanangmaxfi.footballeague.ui.team_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.TeamResponse
import id.web.nanangmaxfi.footballeague.repository.TeamDetailRepository
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F

        presenter = TeamDetailPresenter(this, TeamDetailRepository())
        val name = intent.getStringExtra(EXTRA_NAME)
        actionBar?.title = name

        val idTeam = intent.getStringExtra(EXTRA_ID)
        presenter.getTeamDetail(idTeam)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        layout_detail.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        layout_detail.visibility = View.VISIBLE
    }

    override fun onDataLoaded(data: TeamResponse?) {
        txt_title.text = data?.name
        txt_established.text = data?.established
        txt_stadium.text = data?.stadium
        txt_description.text = data?.description
        Glide.with(applicationContext)
            .load(data?.badge)
            .into(img_badge)
    }

    override fun onDataError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
