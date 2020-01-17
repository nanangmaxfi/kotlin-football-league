package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

data class TeamResponse (
    @SerializedName("idTeam")
    val id: String,

    @SerializedName("strTeam")
    val name: String,

    @SerializedName("strTeamBadge")
    val badge: String,

    @SerializedName("strStadium")
    val stadium: String,

    @SerializedName("intFormedYear")
    val established: String,

    @SerializedName("strDescriptionEN")
    val description: String,

    @SerializedName("strSport")
    val sport: String
)