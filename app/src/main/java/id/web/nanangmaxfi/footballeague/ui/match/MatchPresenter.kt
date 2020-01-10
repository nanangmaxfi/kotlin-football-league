package id.web.nanangmaxfi.footballeague.ui.match

import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.MatchRepository
import id.web.nanangmaxfi.footballeague.utils.EspressoIdlingResource

class MatchPresenter(private val view: MatchView, private val matchRepository: MatchRepository) {

    fun getLastMatch(id: String){
        EspressoIdlingResource.increment()
        view.showLoading()
        matchRepository.getLastMatch(id, object: DetailRepositoryCallback<ListMatchResponse?>{
            override fun onDataLoaded(data: ListMatchResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
                EspressoIdlingResource.decrement()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
                EspressoIdlingResource.decrement()
            }

        })

    }


    fun getNextMatch(id: String){
        EspressoIdlingResource.increment()
        view.showLoading()

        matchRepository.getNextMatch(id, object: DetailRepositoryCallback<ListMatchResponse?>{
            override fun onDataLoaded(data: ListMatchResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
                EspressoIdlingResource.decrement()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getSearch(query: String){
        EspressoIdlingResource.increment()
        view.showLoading()
        matchRepository.getSearchMatch(query, object: DetailRepositoryCallback<ListSearchResponse?>{
            override fun onDataLoaded(data: ListSearchResponse?) {
                view.dataSearch(data)
                view.hideLoading()
                EspressoIdlingResource.decrement()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
                EspressoIdlingResource.decrement()
            }

        })
    }


}