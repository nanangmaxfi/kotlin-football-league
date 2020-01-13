package id.web.nanangmaxfi.footballeague.ui.match

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.MatchRepository
import id.web.nanangmaxfi.footballeague.ui.detail_match.DetailMatchActivity
import id.web.nanangmaxfi.footballeague.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MatchActivity : AppCompatActivity(), MatchView {

    private lateinit var presenter: MatchPresenter

    companion object{
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F
        actionBar?.title = "Match"


        presenter = MatchPresenter(this, MatchRepository())
        val leagueResponse = intent.getParcelableExtra<LeagueResponse>(EXTRA_LEAGUE)
        loadHeader(leagueResponse)

        view_pager.adapter = MatchAdapter(supportFragmentManager, leagueResponse.id)
        tab_layout.setupWithViewPager(view_pager)

    }

    private fun loadHeader(leagueResponse: LeagueResponse){
        txt_title.text = leagueResponse.title
        txt_country.text = leagueResponse.country
        txt_web.text = leagueResponse.website
        Glide.with(applicationContext)
            .load(leagueResponse.badge)
            .into(img_poster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchQuery(searchView)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }

            R.id.search -> {


            }


        }
        return super.onOptionsItemSelected(item)
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
                EspressoIdlingResource.increment()
                presenter.getSearch(newText)
                return true
            }

        })
    }

    override fun onDataError() {

    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        rv_search_match.visibility = View.GONE
        layout_detail.visibility = View.GONE
        txt_not_found.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        rv_search_match.visibility = View.VISIBLE
        layout_detail.visibility = View.GONE
        txt_not_found.visibility = View.GONE
    }

    override fun notFound() {
        progress_bar.visibility = View.GONE
        rv_search_match.visibility = View.GONE
        layout_detail.visibility = View.GONE
        txt_not_found.visibility = View.VISIBLE
    }


    override fun onDataLoaded(match: ListMatchResponse?) {

    }

    override fun dataSearch(dataSearch: ListSearchResponse?) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        val list: MutableList<MatchResponse> = ArrayList()
        for (item in dataSearch!!.matchResponses){
            if (item.sport == "Soccer"){
                list.add(item)
            }

        }

        if (list.isEmpty()){
            notFound()
        }
        else{
            rv_search_match.layoutManager = LinearLayoutManager(this)
            rv_search_match.adapter = MatchSearchAdapter(list){
                val intent = Intent(this, DetailMatchActivity::class.java)
                intent.putExtra(DetailMatchActivity.EXTRA_ID, it.id)
                intent.putExtra(DetailMatchActivity.EXTRA_NAME, "Match")
                startActivity(intent)

            hideLoading()
        }


        }
    }

}
