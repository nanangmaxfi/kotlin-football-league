package id.web.nanangmaxfi.footballeague.ui.match

import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.MatchRepository

class MatchPresenter(private val view: MatchView, private val matchRepository: MatchRepository) {

    fun getLastMatch(id: String){
        view.showLoading()
        matchRepository.getLastMatch(id, object: DetailRepositoryCallback<ListMatchResponse?>{
            override fun onDataLoaded(data: ListMatchResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }

        })

    }


    fun getNextMatch(id: String){
        view.showLoading()

        matchRepository.getNextMatch(id, object: DetailRepositoryCallback<ListMatchResponse?>{
            override fun onDataLoaded(data: ListMatchResponse?) {
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
        matchRepository.getSearchMatch(query, object: DetailRepositoryCallback<ListSearchResponse?>{
            override fun onDataLoaded(data: ListSearchResponse?) {
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