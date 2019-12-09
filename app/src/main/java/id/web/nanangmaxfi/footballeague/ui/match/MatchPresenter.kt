package id.web.nanangmaxfi.footballeague.ui.match

import android.util.Log
import id.web.nanangmaxfi.footballeague.api.ApiMain
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class MatchPresenter(private val view: MatchView) {
    companion object{
        val TAG: String = MatchPresenter::class.java.simpleName
    }

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
                    Log.d(TAG,it.matchResponses.toString())
                    val list: MutableList<MatchResponse> = ArrayList()
                    for (item in it.matchResponses){
                        if (item.sport == "Soccer"){
                            list.add(item)
                            Log.d(TAG,item.homeTeam)
                        }

                    }
                    view.showData(list)
                    view.hideLoading()
                },
                {

                    view.hideLoading()
                }
            )
    }


}