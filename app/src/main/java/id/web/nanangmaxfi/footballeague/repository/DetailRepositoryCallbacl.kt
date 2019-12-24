package id.web.nanangmaxfi.footballeague.repository

interface DetailRepositoryCallback<T> {
    fun onDataLoaded(data: T?)
    fun onDataError()
}