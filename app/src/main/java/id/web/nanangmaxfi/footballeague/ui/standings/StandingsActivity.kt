package id.web.nanangmaxfi.footballeague.ui.standings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.ListStandingResponse
import id.web.nanangmaxfi.footballeague.repository.StandingRepository
import kotlinx.android.synthetic.main.activity_standings.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class StandingsActivity : AppCompatActivity(), StandingView {

    private lateinit var presenter: StandingPresenter

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F
        actionBar?.title = "Standing"

        presenter = StandingPresenter(this, StandingRepository())
        val idLeague = intent.getStringExtra(EXTRA_ID)

        presenter.getStanding(idLeague)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        rv_standings.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        rv_standings.visibility = View.VISIBLE
    }

    override fun onDataLoaded(data: ListStandingResponse?) {
        rv_standings.layoutManager = LinearLayoutManager(this)
        rv_standings.adapter = StandingAdapter(data)
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
