package id.web.nanangmaxfi.footballeague.ui.match

import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

interface MatchView : DetailRepositoryCallback<ListMatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun notFound()
    fun dataSearch(dataSearch: ListSearchResponse?)
}