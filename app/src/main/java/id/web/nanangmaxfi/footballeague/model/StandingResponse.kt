package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

class StandingResponse (
    @SerializedName("name")
    val name: String,

    @SerializedName("teamid")
    val teamId: String,

    @SerializedName("played")
    val played: String,

    @SerializedName("goalsfor")
    val gf: String,

    @SerializedName("goalsagainst")
    val ga: String,

    @SerializedName("win")
    val win: String,

    @SerializedName("draw")
    val draw: String,

    @SerializedName("total")
    val pts: String
)