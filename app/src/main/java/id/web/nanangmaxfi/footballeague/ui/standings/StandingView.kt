package id.web.nanangmaxfi.footballeague.ui.standings

import id.web.nanangmaxfi.footballeague.model.ListStandingResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

interface StandingView : DetailRepositoryCallback<ListStandingResponse> {
    fun showLoading()
    fun hideLoading()
}