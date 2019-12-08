package id.web.nanangmaxfi.footballeague.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.ui.match.MatchActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class DetailActivity : AppCompatActivity(), DetailView {
    lateinit var name: TextView
    lateinit var presenter: DetailPresenter

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F

        presenter = DetailPresenter(this)
        val idLeague = intent.getStringExtra(EXTRA_ID)
        val name = intent.getStringExtra(EXTRA_NAME)

        actionBar?.title = name
        presenter.getDetailData(idLeague)

        layout_detail.setOnRefreshListener {
            presenter.getDetailData(idLeague)
            layout_detail.isRefreshing = false
        }

    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        layout_detail.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        layout_detail.visibility = View.VISIBLE
    }

    override fun showData(data: LeagueResponse?) {
        txt_title.text = data?.title
        txt_country.text = data?.established
        txt_web.text = data?.country
        txt_alternative.text = data?.alternateName
        txt_description.text = data?.description
        Glide.with(applicationContext)
            .load(data?.poster)
            .into(img_poster)

        txt_schedule.setOnClickListener {
            val intent = Intent(this,MatchActivity::class.java)
            intent.putExtra(MatchActivity.EXTRA_LEAGUE, data)
            startActivity(intent)
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
