package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

data class ListTeamResponse (
    @SerializedName("teams")
    val teamResponses: List<TeamResponse>
)