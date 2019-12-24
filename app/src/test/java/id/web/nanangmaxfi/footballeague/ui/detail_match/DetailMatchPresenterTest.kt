package id.web.nanangmaxfi.footballeague.ui.detail_match

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.model.ListSearchResponse
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailMatchRepository
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback
import id.web.nanangmaxfi.footballeague.repository.MatchRepository
import id.web.nanangmaxfi.footballeague.ui.match.MatchPresenter
import id.web.nanangmaxfi.footballeague.ui.match.MatchView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {
    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var matchRepository: DetailMatchRepository

    @Mock
    private lateinit var matchResponse: MatchResponse

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, matchRepository)
    }

    @Test
    fun getDetailMatch() {
        presenter.getDetailMatch("602305")

        argumentCaptor<DetailRepositoryCallback<MatchResponse?>>().apply {
            verify(matchRepository).getDetailMatch(eq("602305"), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        verify(view).showLoading()
        verify(view).onDataLoaded(matchResponse)
        verify(view).hideLoading()
    }
}