package id.web.nanangmaxfi.footballeague.model

class FavoriteTeam (
    val id: Long?,
    val teamId: String?,
    val name: String?,
    val badge: String?,
    val stadium: String?
){
    companion object{
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val NAME: String = "NAME"
        const val BADGE: String = "BADGE"
        const val STADIUM: String = "STADIUM"
    }
}