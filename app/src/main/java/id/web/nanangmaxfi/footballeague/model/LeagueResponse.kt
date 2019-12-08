package id.web.nanangmaxfi.footballeague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueResponse (
    @SerializedName("idLeague")
    val id: String,

    @SerializedName("strLeague")
    val title: String,

    @SerializedName("intFormedYear")
    val established: String,

    @SerializedName("strCountry")
    val country: String,

    @SerializedName("strLeagueAlternate")
    val alternateName: String,

    @SerializedName("strDescriptionEN")
    val description: String,

    @SerializedName("strPoster")
    val poster: String,

    @SerializedName("strWebsite")
    val website: String,

    @SerializedName("strBadge")
    val badge: String
) : Parcelable