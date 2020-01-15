package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

class ListStandingResponse (
    @SerializedName("table")
    val standingResponses: List<StandingResponse>
)