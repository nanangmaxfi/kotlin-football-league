package id.web.nanangmaxfi.footballeague.ui.match.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import id.web.nanangmaxfi.footballeague.ui.detail_match.DetailMatchActivity
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.ui.match.MatchPresenter
import id.web.nanangmaxfi.footballeague.ui.match.MatchView
import id.web.nanangmaxfi.footballeague.ui.match.adapter.NextMatchAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*

class NextMatchFragment(private val idMatch: String) : Fragment(), MatchView {
    private lateinit var adapter: NextMatchAdapter
    private lateinit var presenter: MatchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MatchPresenter(this)
        presenter.getNextMatch(idMatch)

    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        rv_next_match.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        rv_next_match.visibility = View.VISIBLE
    }

    override fun showData(lastMatch: List<MatchResponse>) {
        rv_next_match.layoutManager = LinearLayoutManager(context)
        rv_next_match.adapter = NextMatchAdapter(lastMatch){
            val intent = Intent(context,DetailMatchActivity::class.java)
            intent.putExtra(DetailMatchActivity.EXTRA_ID, it.id)
            intent.putExtra(DetailMatchActivity.EXTRA_NAME, "Match")
            startActivity(intent)
        }
    }
}
