package id.web.nanangmaxfi.footballeague.ui.detail

import id.web.nanangmaxfi.footballeague.model.LeagueResponse

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: LeagueResponse?)
}