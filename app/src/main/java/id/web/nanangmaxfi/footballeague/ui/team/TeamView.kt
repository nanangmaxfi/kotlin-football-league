package id.web.nanangmaxfi.footballeague.ui.team

import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

interface TeamView: DetailRepositoryCallback<ListTeamResponse> {
    fun showLoading()
    fun hideLoading()
}