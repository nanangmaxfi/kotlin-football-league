package id.web.nanangmaxfi.footballeague.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import id.web.nanangmaxfi.footballeague.api.ApiMain
import id.web.nanangmaxfi.footballeague.model.MatchResponse

class DetailMatchRepository {
    fun getDetailMatch(id : String, callback: DetailRepositoryCallback<MatchResponse?>){
        ApiMain().services.getMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({data ->
                data?.let{
                    callback.onDataLoaded(it.matchResponses[0])
                } ?: run {
                    callback.onDataError()
                }
            },{
                callback.onDataError()
            }

            )
    }
}