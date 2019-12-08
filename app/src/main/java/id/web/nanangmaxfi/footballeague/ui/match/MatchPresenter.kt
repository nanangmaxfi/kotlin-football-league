package id.web.nanangmaxfi.footballeague.ui.match

import id.web.nanangmaxfi.footballeague.api.ApiMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchPresenter(private val view: MatchView) {

    fun getLastMatch(id: String){
        view.showLoading()

        ApiMain().services.getLastMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showData(it.matchResponses)
                    view.hideLoading()
                },
                {

                    view.hideLoading()
                }
            )

//        ApiMain().services.getLastMatch(id).enqueue(object :
//            Callback<ListMatchResponse>{
//            override fun onResponse(call: Call<ListMatchResponse>, response: Response<ListMatchResponse>) {
//                if (response.code() == 200){
//                    val lastMatch = response.body()
//                    getListNextMatch(id, lastMatch)
//                }
//            }
//
//            override fun onFailure(call: Call<ListMatchResponse>, t: Throwable) {
//                view.hideLoading()
//            }
//        }
//        )
    }

    fun getNextMatch(id: String){
        view.showLoading()

        ApiMain().services.getNextMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showData(it.matchResponses)
                    view.hideLoading()
                },
                {

                    view.hideLoading()
                }
            )
    }

    fun getSearch(query: String){
        view.showLoading()

        ApiMain().services.getSearchMatch(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showData(it.matchResponses)
                    view.hideLoading()
                },
                {

                    view.hideLoading()
                }
            )
    }


}