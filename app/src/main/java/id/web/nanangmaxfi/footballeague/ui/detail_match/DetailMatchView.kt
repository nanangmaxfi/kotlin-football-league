package id.web.nanangmaxfi.footballeague.ui.detail_match

import id.web.nanangmaxfi.footballeague.model.MatchResponse

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showData(match: MatchResponse)
    fun showLogoHome(homeBadge: String)
    fun showLogoAway(awayBadge: String)
}