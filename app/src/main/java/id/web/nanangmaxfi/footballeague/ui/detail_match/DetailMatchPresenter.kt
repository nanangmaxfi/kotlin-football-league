package id.web.nanangmaxfi.footballeague.ui.detail_match

import id.web.nanangmaxfi.footballeague.api.ApiMain
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailMatchRepository
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMatchPresenter(private val view: DetailMatchView, private val matchRepository: DetailMatchRepository) {

    fun getDetailMatch(id: String){
        view.showLoading()
        matchRepository.getDetailMatch(id, object: DetailRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }

        })

    }

    fun getBadgeHome(id: String?){
        ApiMain().services.getTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showLogoHome(it.teamResponses[0].badge)

                },
                {

                }
            )
    }

    fun getBadgeAway(id: String?){
        ApiMain().services.getTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showLogoAway(it.teamResponses[0].badge)

                },
                {

                }
            )
    }

}