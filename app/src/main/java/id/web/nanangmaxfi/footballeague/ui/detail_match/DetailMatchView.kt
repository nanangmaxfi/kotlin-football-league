package id.web.nanangmaxfi.footballeague.ui.detail_match

import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

interface DetailMatchView : DetailRepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    //fun showData(match: MatchResponse?)
    fun showLogoHome(homeBadge: String)
    fun showLogoAway(awayBadge: String)
}