package id.web.nanangmaxfi.footballeague.ui.team_detail

import id.web.nanangmaxfi.footballeague.model.TeamResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.TeamDetailRepository

class TeamDetailPresenter(private val view: TeamDetailView, private val teamRepository: TeamDetailRepository) {
    fun getTeamDetail(id: String) {
        view.showLoading()
        teamRepository.getTeamDetail(id, object : DetailRepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }
}