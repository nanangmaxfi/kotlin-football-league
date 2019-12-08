package id.web.nanangmaxfi.footballeague.model

import com.google.gson.annotations.SerializedName

data class MatchResponse (
    @SerializedName("idEvent")
    val id: String,

    @SerializedName("strHomeTeam")
    val homeTeam: String,

    @SerializedName("strAwayTeam")
    val awayTeam: String,

    @SerializedName("dateEvent")
    val date: String,

    @SerializedName("strTime")
    val time: String,

    @SerializedName("intHomeScore")
    val homeScore: String,

    @SerializedName("intAwayScore")
    val awayScore: String,

    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails: String,

    @SerializedName("strHomeRedCards")
    val homeRedCards: String,

    @SerializedName("strHomeYellowCards")
    val homeYellowCards: String,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeGoalkeeper: String,

    @SerializedName("strHomeLineupDefense")
    val homeDefense: String,

    @SerializedName("strHomeLineupMidfield")
    val homeMidfield: String,

    @SerializedName("strHomeLineupForward")
    val homeForward: String,

    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails: String,

    @SerializedName("strAwayRedCards")
    val awayRedCards: String,

    @SerializedName("strAwayYellowCards")
    val awayYellowCards: String,

    @SerializedName("strAwayLineupGoalkeeper")
    val awayGoalkeeper: String,

    @SerializedName("strAwayLineupDefense")
    val awayDefense: String,

    @SerializedName("strAwayLineupMidfield")
    val awayMidfield: String,

    @SerializedName("strAwayLineupForward")
    val awayForward: String,

    @SerializedName("idHomeTeam")
    val idHomeTeam: String,

    @SerializedName("idAwayTeam")
    val idAwayTeam: String

)