package id.web.nanangmaxfi.footballeague.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import id.web.nanangmaxfi.footballeague.api.ApiMain
import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse

class MatchRepository {
    fun getLastMatch(id : String, callback: DetailRepositoryCallback<ListMatchResponse?>){
        ApiMain().services.getLastMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({data ->
                data?.let{
                    callback.onDataLoaded(it)
                } ?: run {
                    callback.onDataError()
                }
            },{
                callback.onDataError()
            }

            )
    }

    fun getNextMatch(id : String, callback: DetailRepositoryCallback<ListMatchResponse?>){
        ApiMain().services.getNextMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({data ->
                data?.let{
                    callback.onDataLoaded(it)
                } ?: run {
                    callback.onDataError()
                }
            },{
                callback.onDataError()
            }

            )
    }

    fun getSearchMatch(query : String, callback: DetailRepositoryCallback<ListSearchResponse?>){
        ApiMain().services.getSearchMatch(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({data ->
                data?.let{
                    callback.onDataLoaded(it)
                } ?: run {
                    callback.onDataError()
                }
            },{
                callback.onDataError()
            }

            )
    }
}