package id.web.nanangmaxfi.footballeague.ui.team_detail

import id.web.nanangmaxfi.footballeague.model.TeamResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

interface TeamDetailView : DetailRepositoryCallback<TeamResponse> {
    fun showLoading()
    fun hideLoading()
}