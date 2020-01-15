package id.web.nanangmaxfi.footballeague.ui.standings

import id.web.nanangmaxfi.footballeague.model.ListStandingResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.StandingRepository

class StandingPresenter(private val view: StandingView, private val standingRepository: StandingRepository) {

    fun getStanding(id: String){
        view.showLoading()
        standingRepository.getStanding(id, object: DetailRepositoryCallback<ListStandingResponse?>{
            override fun onDataLoaded(data: ListStandingResponse?) {
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