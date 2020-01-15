package id.web.nanangmaxfi.footballeague.repository

import id.web.nanangmaxfi.footballeague.model.ListStandingResponse
import id.web.nanangmaxfi.footballeague.api.ApiMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StandingRepository {
    fun getStanding(id: String, callback: DetailRepositoryCallback<ListStandingResponse?>){
        ApiMain().services.getStanding(id)
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