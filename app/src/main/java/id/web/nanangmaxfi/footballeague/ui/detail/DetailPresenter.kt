package id.web.nanangmaxfi.footballeague.ui.detail

import id.web.nanangmaxfi.footballeague.api.ApiMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(private val view: DetailView) {


    fun getDetailData(id : String?){
        view.showLoading()

        ApiMain().services.getDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe (
                {
                    view.showData(it.leagueResponses[0])
                    view.hideLoading()
                },
                {

                    view.hideLoading()
                }
            )
    }
}