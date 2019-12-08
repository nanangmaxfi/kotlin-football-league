package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

data class ListSearchResponse (
    @SerializedName("event")
    val matchResponses: List<MatchResponse>
)