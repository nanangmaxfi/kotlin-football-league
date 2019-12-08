package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

data class ListLeagueResponse (
    @SerializedName("leagues")
    val leagueResponses: List<LeagueResponse>
)