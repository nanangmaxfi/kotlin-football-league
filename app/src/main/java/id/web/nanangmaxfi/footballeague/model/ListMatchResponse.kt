package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

data class ListMatchResponse (
    @SerializedName("events")
    val matchResponses: List<MatchResponse>
)