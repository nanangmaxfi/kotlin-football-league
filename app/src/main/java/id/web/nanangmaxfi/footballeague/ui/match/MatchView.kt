package id.web.nanangmaxfi.footballeague.ui.match

import id.web.nanangmaxfi.footballeague.model.MatchResponse

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showData(match: List<MatchResponse>)
}