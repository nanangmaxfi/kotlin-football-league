package id.web.nanangmaxfi.footballeague.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils() {

    fun dateFormat(dateTime: String?): String{
        val parser =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return formatter.format(parser.parse(dateTime))
    }

    fun timeFormat(dateTime: String?): String{
        val parser =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(parser.parse(dateTime))
    }
}