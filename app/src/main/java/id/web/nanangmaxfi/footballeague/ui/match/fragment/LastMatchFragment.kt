package id.web.nanangmaxfi.footballeague.ui.match.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import id.web.nanangmaxfi.footballeague.ui.detail_match.DetailMatchActivity
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.model.TeamResponse
import id.web.nanangmaxfi.footballeague.ui.match.MatchPresenter
import id.web.nanangmaxfi.footballeague.ui.match.MatchView
import id.web.nanangmaxfi.footballeague.ui.match.adapter.LastMatchAdapter
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import id.web.nanangmaxfi.footballeague.repository.MatchRepository
import id.web.nanangmaxfi.footballeague.utils.EspressoIdlingResource

class LastMatchFragment(private val idMatch: String) : Fragment(), MatchView {
    override fun dataSearch(dataSearch: ListSearchResponse?) {

    }

    private lateinit var presenter: MatchPresenter
    private lateinit var matchList: List<MatchResponse>
    private lateinit var badge: List<TeamResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MatchPresenter(this, MatchRepository())
        EspressoIdlingResource.increment()
        presenter.getLastMatch(idMatch)

    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        rv_last_match.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        rv_last_match.visibility = View.VISIBLE
    }

    override fun notFound() {

    }


    override fun onDataLoaded(lastMatch: ListMatchResponse?) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        rv_last_match.layoutManager = LinearLayoutManager(context)
        rv_last_match.adapter = LastMatchAdapter(lastMatch){
                val intent = Intent(context,DetailMatchActivity::class.java)
                intent.putExtra(DetailMatchActivity.EXTRA_ID, it.id)
                intent.putExtra(DetailMatchActivity.EXTRA_NAME, "Match")
                startActivity(intent)

        }
    }

    override fun onDataError() {
    }

}
