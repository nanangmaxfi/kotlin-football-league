package id.web.nanangmaxfi.footballeague.repository

import id.web.nanangmaxfi.footballeague.model.ListTeamResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import id.web.nanangmaxfi.footballeague.api.ApiMain
import id.web.nanangmaxfi.footballeague.model.TeamResponse

class TeamDetailRepository {

    fun getTeamDetail(id: String, callback: DetailRepositoryCallback<TeamResponse?>){
        ApiMain().services.getTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({data ->
                data?.let{
                    callback.onDataLoaded(it.teamResponses[0])
                } ?: run {
                    callback.onDataError()
                }
            },{
                callback.onDataError()
            }

            )
    }
}