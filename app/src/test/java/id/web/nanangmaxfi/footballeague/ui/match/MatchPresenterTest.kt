package id.web.nanangmaxfi.footballeague.ui.match

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepository
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.MatchRepository
import id.web.nanangmaxfi.footballeague.ui.detail.DetailPresenter
import id.web.nanangmaxfi.footballeague.ui.detail.DetailView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private lateinit var listMatchResponse: ListMatchResponse

    @Mock
    private lateinit var listSearchResponse: ListSearchResponse

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, matchRepository)
    }

    @Test
    fun getLastMatch() {
        presenter.getLastMatch("602305")

        argumentCaptor<DetailRepositoryCallback<ListMatchResponse?>>().apply {
            verify(matchRepository).getLastMatch(eq("602305"), capture())
            firstValue.onDataLoaded(listMatchResponse)
        }

        verify(view).showLoading()
        verify(view).onDataLoaded(listMatchResponse)
        verify(view).hideLoading()
    }

    @Test
    fun getNextMatch() {
        presenter.getNextMatch("602309")

        argumentCaptor<DetailRepositoryCallback<ListMatchResponse?>>().apply {
            verify(matchRepository).getNextMatch(eq("602309"), capture())
            firstValue.onDataLoaded(listMatchResponse)
        }

        verify(view).showLoading()
        verify(view).onDataLoaded(listMatchResponse)
        verify(view).hideLoading()
    }

    @Test
    fun getSearch() {
        presenter.getSearch("liverpool")

        argumentCaptor<DetailRepositoryCallback<ListSearchResponse?>>().apply {
            verify(matchRepository).getSearchMatch(eq("liverpool"), capture())
            firstValue.onDataLoaded(listSearchResponse)
        }

        verify(view).showLoading()
        verify(view).dataSearch(listSearchResponse)
        verify(view).hideLoading()
    }
}