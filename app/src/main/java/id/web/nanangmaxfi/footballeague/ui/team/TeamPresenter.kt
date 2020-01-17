package id.web.nanangmaxfi.footballeague.ui.team

import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.TeamRepository

class TeamPresenter(private val view: TeamView, private val teamRepository: TeamRepository) {
    fun getTeam(id: String) {
        view.showLoading()
        teamRepository.getAllTeam(id, object : DetailRepositoryCallback<ListTeamResponse?> {
            override fun onDataLoaded(data: ListTeamResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }

    fun getSearch(query: String){
        view.showLoading()
        teamRepository.getSearchTeam(query, object: DetailRepositoryCallback<ListTeamResponse?>{
            override fun onDataLoaded(data: ListTeamResponse?) {
                view.dataSearch(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }

        })
    }
}