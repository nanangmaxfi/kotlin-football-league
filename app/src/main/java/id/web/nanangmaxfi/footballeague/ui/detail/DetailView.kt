package id.web.nanangmaxfi.footballeague.ui.detail

import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

interface DetailView : DetailRepositoryCallback<LeagueResponse> {
    fun showLoading()
    fun hideLoading()
}