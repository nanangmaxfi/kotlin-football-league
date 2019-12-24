package id.web.nanangmaxfi.footballeague.ui.detail


import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepository
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback


class DetailPresenter(private val view: DetailView, private val leagueRepository: DetailRepository) {


    fun getDetailData(id : String){
        view.showLoading()
        leagueRepository.getLeague(id, object: DetailRepositoryCallback<LeagueResponse?>{
            override fun onDataLoaded(data: LeagueResponse?) {
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