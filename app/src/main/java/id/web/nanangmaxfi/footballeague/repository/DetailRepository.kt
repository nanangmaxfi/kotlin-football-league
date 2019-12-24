package id.web.nanangmaxfi.footballeague.repository

import id.web.nanangmaxfi.footballeague.api.ApiMain
import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailRepository {
    fun getLeague(id : String, callback: DetailRepositoryCallback<LeagueResponse?>){
        ApiMain().services.getDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({data ->
                data?.let{
                    callback.onDataLoaded(it.leagueResponses[0])
                } ?: run {
                    callback.onDataError()
                }
            },{
                callback.onDataError()
            }

            )
    }
}