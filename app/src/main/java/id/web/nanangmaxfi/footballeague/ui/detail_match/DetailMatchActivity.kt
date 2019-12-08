package id.web.nanangmaxfi.footballeague.ui.detail_match

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.utils.DateUtils
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    lateinit var presenter: DetailMatchPresenter

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F

        presenter = DetailMatchPresenter(this)
        val idMatch = intent.getStringExtra(EXTRA_ID)
        val name = intent.getStringExtra(EXTRA_NAME)

        actionBar?.title = name
        presenter.getDetailMatch(idMatch)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        layout_scroll.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        layout_scroll.visibility = View.VISIBLE
    }

    override fun showData(match: MatchResponse) {
        txt_date.text = DateUtils().dateFormat(match.date+" "+match.time)
        txt_time.text = DateUtils().timeFormat(match.date+" "+match.time)
        name_home.text = match.homeTeam
        name_away.text = match.awayTeam
        score_home.text = match.homeScore
        score_away.text = match.awayScore
        goal_home.text = match.homeGoalDetails
        goal_away.text = match.awayGoalDetails
        redcard_home.text = match.homeRedCards
        redcard_away.text = match.awayRedCards
        yellowcard_home.text = match.homeYellowCards
        yellowcard_away.text = match.awayYellowCards

        presenter.getBadgeHome(match.idHomeTeam)
        presenter.getBadgeAway(match.idAwayTeam)
    }

    override fun showLogoHome(homeBadge: String) {
        Glide.with(applicationContext)
            .load(homeBadge)
            .into(logo_home)
    }

    override fun showLogoAway(awayBadge: String) {
        Glide.with(applicationContext)
            .load(awayBadge)
            .into(logo_away)
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
