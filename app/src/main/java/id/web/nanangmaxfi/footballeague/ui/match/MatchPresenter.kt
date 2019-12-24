package id.web.nanangmaxfi.footballeague.ui.match

import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.MatchRepository

class MatchPresenter(private val view: MatchView, private val matchRepository: MatchRepository) {
    companion object{
        val TAG: String = MatchPresenter::class.java.simpleName
    }

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

//        ApiMain().services.getLastMatch(id)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.newThread())
//            .subscribe (
//                {
//
//                    view.showData(it.matchResponses)
//                    view.hideLoading()
//                },
//                {
//
//                    view.hideLoading()
//                }
//            )

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
//                val list: MutableList<MatchResponse> = ArrayList()
//                    for (item in data!!.matchResponses){
//                        if (item.sport == "Soccer"){
//                            list.add(item)
//                        }
//
//                    }
//
//                    if (list.isEmpty()){
//                        view.notFound()
//                    }
//                    else{
//                        view.dataSearch(list)
//                        view.hideLoading()
//                    }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }

        })

//        ApiMain().services.getSearchMatch(query)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.newThread())
//            .subscribe (
//                {
//                    Log.d(TAG,it.matchResponses.toString())
//                    val list: MutableList<MatchResponse> = ArrayList()
//                    for (item in it.matchResponses){
//                        if (item.sport == "Soccer"){
//                            list.add(item)
//                            Log.d(TAG,item.homeTeam)
//                        }
//
//                    }
//
//                    if (list.isEmpty()){
//                        view.notFound()
//                    }
//                    else{
//                        view.showData(list)
//                        view.hideLoading()
//                    }
//
//                },
//                {
//
//                    view.hideLoading()
//                }
//            )
    }


}