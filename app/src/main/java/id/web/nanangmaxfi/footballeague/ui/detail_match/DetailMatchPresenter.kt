package id.web.nanangmaxfi.footballeague.ui.detail_match

import id.web.nanangmaxfi.footballeague.api.ApiMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMatchPresenter(private val view: DetailMatchView) {

    fun getDetailMatch(id: String){
        view.showLoading()

       ApiMain().services.getMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showData(it.matchResponses[0])
                    view.hideLoading()
                },
                {

                    view.hideLoading()
                }
            )
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