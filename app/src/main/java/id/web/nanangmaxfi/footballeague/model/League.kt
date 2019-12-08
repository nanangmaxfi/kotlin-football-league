package id.web.nanangmaxfi.footballeague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(val id:String,
                  val name: String,
                  val image: Int) : Parcelable