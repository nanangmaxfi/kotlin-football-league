package id.web.nanangmaxfi.footballeague.ui.detail

import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.repository.DetailRepository
import id.web.nanangmaxfi.footballeague.repository.DetailRepositoryCallback

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var detailRepository: DetailRepository

    @Mock
    private lateinit var leagueResponse: LeagueResponse

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, detailRepository)
    }

    @Test
    fun getDetailData() {

        presenter.getDetailData("4328")

        argumentCaptor<DetailRepositoryCallback<LeagueResponse?>>().apply {
            verify(detailRepository).getLeague(eq("4328"), capture())
            firstValue.onDataLoaded(leagueResponse)

        }

        verify(view).showLoading()
        verify(view).onDataLoaded(leagueResponse)
        verify(view).hideLoading()

    }

    @Test
    fun getDetailError(){
        presenter.getDetailData("")

        argumentCaptor<DetailRepositoryCallback<LeagueResponse?>>().apply {
            verify(detailRepository).getLeague(eq(""), capture())
            firstValue.onDataLoaded(leagueResponse)

        }

        verify(view).showLoading()
        verify(view).onDataLoaded(leagueResponse)
        verify(view).hideLoading()
    }
}