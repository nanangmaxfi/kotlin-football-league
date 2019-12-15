package id.web.nanangmaxfi.footballeague.model

data class Favorite (
    val id: Long?,
    val matchId: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val date: String?,
    val time: String?,
    val homeScore: String?,
    val awayScore: String?,
    val matchType: String?
){
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_MATCH: String = "ID_MATCH"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val DATE: String = "DATE"
        const val TIME: String = "TIME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val MATCH_TYPE: String = "MATCH_TYPE"
    }
}