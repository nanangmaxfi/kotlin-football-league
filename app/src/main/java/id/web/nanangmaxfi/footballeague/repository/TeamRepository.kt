package id.web.nanangmaxfi.footballeague.repository

import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
import id.web.nanangmaxfi.footballeague.api.ApiMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeamRepository {

    fun getAllTeam(id: String, callback: DetailRepositoryCallback<ListTeamResponse?>){
        ApiMain().services.getAllTeam(id)
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