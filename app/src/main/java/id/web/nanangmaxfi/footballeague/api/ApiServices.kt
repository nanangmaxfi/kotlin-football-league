package id.web.nanangmaxfi.footballeague.api

import id.web.nanangmaxfi.footballeague.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("lookupleague.php")
    fun getDetail(@Query("id") id: String?) : Observable<ListLeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?) : Observable<ListMatchResponse>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: String?) : Observable<ListMatchResponse>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String?) : Observable<ListTeamResponse>

    @GET("lookupevent.php")
    fun getMatch(@Query("id") id: String?) : Observable<ListMatchResponse>

    @GET("searchevents.php")
    fun getSearchMatch(@Query("e") query: String?) : Observable<ListSearchResponse>

    @GET("lookuptable.php")
    fun getStanding(@Query("l") id: String?) : Observable<ListStandingResponse>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id: String?) : Observable<ListTeamResponse>
}